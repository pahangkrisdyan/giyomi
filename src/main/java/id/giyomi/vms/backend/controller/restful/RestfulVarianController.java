package id.giyomi.vms.backend.controller.restful;

import id.giyomi.vms.backend.controller.restful.model.VarianRestfulModel;
import id.giyomi.vms.backend.entity.Varian;
import id.giyomi.vms.backend.repository.KoleksiRepository;
import id.giyomi.vms.backend.repository.VarianRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/restful/varians")
public class RestfulVarianController implements RestfulController<VarianRestfulModel, Varian> {

    private VarianRepository varianRepository;
    private KoleksiRepository koleksiRepository;

    public RestfulVarianController(VarianRepository varianRepository, KoleksiRepository koleksiRepository) {
        this.varianRepository = varianRepository;
        this.koleksiRepository = koleksiRepository;
    }

    @Override
    public Varian findById(Long id){
        Varian varian = varianRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found varian with id " + id));
        return varian;
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<VarianRestfulModel> getSingle(Long id) {
        return ResponseEntity.ok(new VarianRestfulModel(findById(id)));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<VarianRestfulModel>> getList() {
        ArrayList<VarianRestfulModel> varianRestfulModels = new ArrayList<>();
        varianRepository.findAll().forEach(varian -> {
            varianRestfulModels.add(new VarianRestfulModel(varian));
        });
        return ResponseEntity.ok(varianRestfulModels);
    }

    @Override
    @PostMapping
    public ResponseEntity<VarianRestfulModel> create(@RequestBody VarianRestfulModel varianRestfulModel) {
        return ResponseEntity.ok(new VarianRestfulModel(varianRepository.save(varianRestfulModel.getEntity(koleksiRepository))));
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<VarianRestfulModel> update(@PathVariable Long id, @RequestBody VarianRestfulModel varianRestfulModel) {
        return ResponseEntity.ok(new VarianRestfulModel(varianRepository.save(varianRestfulModel.getEntityUpdate(findById(id), koleksiRepository))));
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(Long id) {
        findById(id);
        varianRepository.deleteById(id);
        return ResponseEntity.ok("Process with id " + id + " deleted successfully");
    }
}
