package id.giyomi.vms.backend.controller.restful;

import id.giyomi.vms.backend.controller.restful.model.UkuranRestfulModel;
import id.giyomi.vms.backend.entity.Ukuran;
import id.giyomi.vms.backend.repository.UkuranRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/restful/ukurans")
public class RestfulUkuranController implements RestfulController<UkuranRestfulModel, Ukuran> {

    private UkuranRepository ukuranRepository;

    public RestfulUkuranController(UkuranRepository ukuranRepository) {
        this.ukuranRepository = ukuranRepository;
    }

    public Ukuran findById(Long id){
        Ukuran ukuran = ukuranRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found ukuran with id " + id));
        return ukuran;
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<UkuranRestfulModel> getSingle(Long id) {
        return ResponseEntity.ok(new UkuranRestfulModel(findById(id)));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<UkuranRestfulModel>> getList() {
        ArrayList<UkuranRestfulModel> ukuranRestfulModels = new ArrayList<>();
        ukuranRepository.findAll().forEach(ukuran -> {
            ukuranRestfulModels.add(new UkuranRestfulModel(ukuran));
        });
        return ResponseEntity.ok(ukuranRestfulModels);
    }

    @Override
    @PostMapping
    public ResponseEntity<UkuranRestfulModel> create(@RequestBody UkuranRestfulModel ukuranRestfulModel) {
        return ResponseEntity.ok(new UkuranRestfulModel(ukuranRepository.save(ukuranRestfulModel.getEntity())));
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<UkuranRestfulModel> update(@PathVariable Long id, @RequestBody UkuranRestfulModel ukuranRestfulModel) {
        return ResponseEntity.ok(new UkuranRestfulModel(ukuranRepository.save(ukuranRestfulModel.getEntityUpdate(findById(id)))));
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(Long id) {
        findById(id);
        ukuranRepository.deleteById(id);
        return ResponseEntity.ok("Ukuran with id " + id + " deleted successfully");
    }
}
