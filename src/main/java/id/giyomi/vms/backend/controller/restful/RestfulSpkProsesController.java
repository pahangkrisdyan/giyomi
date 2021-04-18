package id.giyomi.vms.backend.controller.restful;

import id.giyomi.vms.backend.controller.rest.model.SpkStatusEnum;
import id.giyomi.vms.backend.controller.restful.model.SpkProsesRestfulModel;
import id.giyomi.vms.backend.controller.restful.model.SpkProsesRestfulModel;
import id.giyomi.vms.backend.entity.AuditModel;
import id.giyomi.vms.backend.entity.SpkProses;
import id.giyomi.vms.backend.entity.SpkProses;
import id.giyomi.vms.backend.entity.SpkStatus;
import id.giyomi.vms.backend.repository.*;
import id.giyomi.vms.backend.repository.SpkProsesRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/restful/spkProseses")
public class RestfulSpkProsesController implements RestfulController<SpkProsesRestfulModel, SpkProses> {

    private SpkProsesRepository spkProsesRepository;
    private SpkRepository spkRepository;
    private ProsesRepository prosesRepository;
    private SpkStatusRepository spkStatusRepository;

    public RestfulSpkProsesController(SpkProsesRepository spkProsesRepository, SpkRepository spkRepository, ProsesRepository prosesRepository, SpkStatusRepository spkStatusRepository) {
        this.spkProsesRepository = spkProsesRepository;
        this.spkRepository = spkRepository;
        this.prosesRepository = prosesRepository;
        this.spkStatusRepository = spkStatusRepository;
    }

    @Override
    public SpkProses findById(Long id){
        SpkProses spkProses = spkProsesRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found spkProses with id " + id));
        return spkProses;
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<SpkProsesRestfulModel> getSingle(Long id) {
        return ResponseEntity.ok(new SpkProsesRestfulModel(findById(id)));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<SpkProsesRestfulModel>> getList() {
        ArrayList<SpkProsesRestfulModel> spkProsesRestfulModels = new ArrayList<>();
        spkProsesRepository.findAll().forEach(spkProses -> {
            spkProsesRestfulModels.add(new SpkProsesRestfulModel(spkProses));
        });
        return ResponseEntity.ok(spkProsesRestfulModels);
    }

    @Override
    @PostMapping
    public ResponseEntity<SpkProsesRestfulModel> create(@RequestBody SpkProsesRestfulModel spkProsesRestfulModel) {
        return ResponseEntity.ok(new SpkProsesRestfulModel(spkProsesRepository.save(spkProsesRestfulModel.getEntity(spkRepository, prosesRepository))));
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<SpkProsesRestfulModel> update(@PathVariable Long id, @RequestBody SpkProsesRestfulModel spkProsesRestfulModel) {
        List<SpkStatus> spkStatuses = spkStatusRepository.findBySpkId(spkProsesRestfulModel.getSpkId());
//        spkStatuses.sort(Comparator.comparing(AuditModel::getCreatedAt));
//        SpkStatus lastStatus = spkStatuses.get(spkStatuses.size()-1);
//        if(lastStatus.getStatus().getNama().equals(SpkStatusEnum.ON_GOING))
//            throw new UnsupportedOperationException("SPK does not on going yet, change SPK status to be on going first!");
        return ResponseEntity.ok(new SpkProsesRestfulModel(spkProsesRepository.save(spkProsesRestfulModel.getEntityUpdate(findById(id), spkRepository, prosesRepository))));
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(Long id) {
        findById(id);
        spkProsesRepository.deleteById(id);
        return ResponseEntity.ok("SpkProses with id " + id + " deleted successfully");
    }
}
