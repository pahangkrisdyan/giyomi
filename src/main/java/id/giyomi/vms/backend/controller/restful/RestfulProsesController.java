package id.giyomi.vms.backend.controller.restful;

import id.giyomi.vms.backend.controller.restful.model.ProsesRestfulModel;
import id.giyomi.vms.backend.entity.Proses;
import id.giyomi.vms.backend.repository.ProsesRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/restful/prosess")
public class RestfulProsesController implements RestfulController<ProsesRestfulModel, Proses> {

    private ProsesRepository prosesRepository;

    public RestfulProsesController(ProsesRepository prosesRepository) {
        this.prosesRepository = prosesRepository;
    }

    @Override
    public Proses findById(Long id){
        Proses proses = prosesRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found proses with id " + id));
        return proses;
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProsesRestfulModel> getSingle(Long id) {
        return ResponseEntity.ok(new ProsesRestfulModel(findById(id)));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ProsesRestfulModel>> getList() {
        ArrayList<ProsesRestfulModel> prosesRestfulModels = new ArrayList<>();
        prosesRepository.findAll().forEach(proses -> {
            prosesRestfulModels.add(new ProsesRestfulModel(proses));
        });
        return ResponseEntity.ok(prosesRestfulModels);
    }

    @Override
    @PostMapping
    public ResponseEntity<ProsesRestfulModel> create(@RequestBody ProsesRestfulModel prosesRestfulModel) {
        return ResponseEntity.ok(new ProsesRestfulModel(prosesRepository.save(prosesRestfulModel.getEntity())));
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProsesRestfulModel> update(@PathVariable Long id, @RequestBody ProsesRestfulModel prosesRestfulModel) {
        return ResponseEntity.ok(new ProsesRestfulModel(prosesRepository.save(prosesRestfulModel.getEntityUpdate(findById(id)))));
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(Long id) {
        findById(id);
        prosesRepository.deleteById(id);
        return ResponseEntity.ok("Proces with id " + id + " deleted successfully");
    }

}
