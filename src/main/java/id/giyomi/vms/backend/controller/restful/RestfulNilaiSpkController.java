package id.giyomi.vms.backend.controller.restful;

import id.giyomi.vms.backend.controller.restful.model.NilaiSpkRestfulModel;
import id.giyomi.vms.backend.entity.NilaiSpk;
import id.giyomi.vms.backend.entity.NilaiSpk;
import id.giyomi.vms.backend.repository.NilaiSpkRepository;
import id.giyomi.vms.backend.repository.NilaiSpkRepository;
import id.giyomi.vms.backend.repository.SpkProsesRepository;
import id.giyomi.vms.backend.repository.SpkRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/restful/nilaispks")
public class RestfulNilaiSpkController
        implements RestfulController<NilaiSpkRestfulModel, NilaiSpk> {

    private SpkRepository spkRepository;
    private NilaiSpkRepository nilaiSpkRepository;

    public RestfulNilaiSpkController(SpkRepository spkRepository, NilaiSpkRepository nilaiSpkRepository) {
        this.spkRepository = spkRepository;
        this.nilaiSpkRepository = nilaiSpkRepository;
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<NilaiSpkRestfulModel> getSingle(Long id) {
        return ResponseEntity.ok(new NilaiSpkRestfulModel(findById(id)));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<NilaiSpkRestfulModel>> getList() {
        ArrayList<NilaiSpkRestfulModel> nilaiSpkRestfulModels = new ArrayList<>();
        nilaiSpkRepository.findAll().forEach(nilaiSpk -> {
            nilaiSpkRestfulModels.add(new NilaiSpkRestfulModel(nilaiSpk));
        });
        return ResponseEntity.ok(nilaiSpkRestfulModels);
    }

    @Override
    @PostMapping
    public ResponseEntity<NilaiSpkRestfulModel> create(@RequestBody NilaiSpkRestfulModel nilaiSpkRestfulModel) {
        return ResponseEntity.ok(new NilaiSpkRestfulModel(nilaiSpkRepository.save(nilaiSpkRestfulModel.getEntity(spkRepository, nilaiSpkRepository))));
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<NilaiSpkRestfulModel> update(@PathVariable Long id, @RequestBody NilaiSpkRestfulModel nilaiSpkRestfulModel) {
        return ResponseEntity.ok(new NilaiSpkRestfulModel(nilaiSpkRepository.save(nilaiSpkRestfulModel.getEntityUpdate(findById(id), spkRepository))));
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(Long id) {
        findById(id);
        nilaiSpkRepository.deleteById(id);
        return ResponseEntity.ok("NilaiSpk with id " + id + " deleted successfully");
    }

    @Override
    public NilaiSpk findById(Long id){
        NilaiSpk nilaiSpk = nilaiSpkRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found nilaiSpk with id " + id));
        return nilaiSpk;
    }
}
