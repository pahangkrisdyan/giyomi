package id.giyomi.vms.backend.controller.restful;

import id.giyomi.vms.backend.controller.restful.model.SpkRestfulModel;
import id.giyomi.vms.backend.entity.Spk;
import id.giyomi.vms.backend.entity.Vendor;
import id.giyomi.vms.backend.repository.SpkRepository;
import id.giyomi.vms.backend.repository.SpkProsesRepository;
import id.giyomi.vms.backend.repository.VendorRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/restful/spks")
public class RestfulSpkController implements RestfulController<SpkRestfulModel, Spk> {

    private SpkRepository spkRepository;
    private VendorRepository vendorRepository;

    public RestfulSpkController(SpkRepository spkRepository, VendorRepository vendorRepository) {
        this.spkRepository = spkRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Spk findById(Long id){
        Spk Spk = spkRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found Spk with id " + id));
        return Spk;
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<SpkRestfulModel> getSingle(Long id) {
        return ResponseEntity.ok(new SpkRestfulModel(findById(id)));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<SpkRestfulModel>> getList() {
        ArrayList<SpkRestfulModel> spkRestfulModels = new ArrayList<>();
        spkRepository.findAll().forEach(Spk -> {
            spkRestfulModels.add(new SpkRestfulModel(Spk));
        });
        return ResponseEntity.ok(spkRestfulModels);
    }

    @Override
    @PostMapping
    public ResponseEntity<SpkRestfulModel> create(@RequestBody SpkRestfulModel spkRestfulModel) {
        return ResponseEntity.ok(new SpkRestfulModel(spkRepository.save(spkRestfulModel.getEntity(vendorRepository))));
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<SpkRestfulModel> update(@PathVariable Long id, @RequestBody SpkRestfulModel SpkRestfulModel) {
        return ResponseEntity.ok(new SpkRestfulModel(spkRepository.save(SpkRestfulModel.getEntityUpdate(findById(id), vendorRepository))));
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(Long id) {
        findById(id);
        spkRepository.deleteById(id);
        return ResponseEntity.ok("Spk with id " + id + " deleted successfully");
    }

}
