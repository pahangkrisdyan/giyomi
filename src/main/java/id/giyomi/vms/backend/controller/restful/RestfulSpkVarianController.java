package id.giyomi.vms.backend.controller.restful;

import id.giyomi.vms.backend.controller.restful.model.SpkVarianRestfulModel;
import id.giyomi.vms.backend.entity.SpkVarian;
import id.giyomi.vms.backend.repository.PanjangKainRepository;
import id.giyomi.vms.backend.repository.SpkRepository;
import id.giyomi.vms.backend.repository.SpkVarianRepository;
import id.giyomi.vms.backend.repository.VarianRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/restful/spkVarians")
public class RestfulSpkVarianController implements RestfulController<SpkVarianRestfulModel, SpkVarian> {

    private SpkVarianRepository spkVarianRepository;
    private SpkRepository spkRepository;
    private VarianRepository varianRepository;
    private PanjangKainRepository panjangKainRepository;

    public RestfulSpkVarianController(SpkVarianRepository spkVarianRepository, SpkRepository spkRepository, VarianRepository varianRepository, PanjangKainRepository panjangKainRepository) {
        this.spkVarianRepository = spkVarianRepository;
        this.spkRepository = spkRepository;
        this.varianRepository = varianRepository;
        this.panjangKainRepository = panjangKainRepository;
    }

    @Override
    public SpkVarian findById(Long id){
        SpkVarian spkVarian = spkVarianRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found spkVarian with id " + id));
        return spkVarian;
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<SpkVarianRestfulModel> getSingle(Long id) {
        return ResponseEntity.ok(new SpkVarianRestfulModel(findById(id)));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<SpkVarianRestfulModel>> getList() {
        ArrayList<SpkVarianRestfulModel> spkVarianRestfulModels = new ArrayList<>();
        spkVarianRepository.findAll().forEach(spkVarian -> {
            spkVarianRestfulModels.add(new SpkVarianRestfulModel(spkVarian));
        });
        return ResponseEntity.ok(spkVarianRestfulModels);
    }

    @Override
    @PostMapping
    public ResponseEntity<SpkVarianRestfulModel> create(@RequestBody SpkVarianRestfulModel spkVarianRestfulModel) {
        return ResponseEntity.ok(new SpkVarianRestfulModel(spkVarianRepository.save(spkVarianRestfulModel.getEntity(spkRepository, varianRepository, panjangKainRepository))));
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<SpkVarianRestfulModel> update(@PathVariable Long id, @RequestBody SpkVarianRestfulModel spkVarianRestfulModel) {
        return ResponseEntity.ok(new SpkVarianRestfulModel(spkVarianRepository.save(spkVarianRestfulModel.getEntityUpdate(findById(id), spkRepository, varianRepository, panjangKainRepository))));
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(Long id) {
        findById(id);
        spkVarianRepository.deleteById(id);
        return ResponseEntity.ok("SpkVarian with id " + id + " deleted successfully");
    }
}
