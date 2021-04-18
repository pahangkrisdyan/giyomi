package id.giyomi.vms.backend.controller.restful;

import id.giyomi.vms.backend.controller.restful.model.SatuanPanjangKainRestfulModel;
import id.giyomi.vms.backend.controller.restful.model.SatuanPanjangKainRestfulModel;
import id.giyomi.vms.backend.entity.SatuanPanjangKain;
import id.giyomi.vms.backend.entity.SatuanPanjangKain;
import id.giyomi.vms.backend.repository.SatuanPanjangKainRepository;
import id.giyomi.vms.backend.repository.SatuanPanjangKainRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/restful/satuanpanjangkains")
public class RestfulSatuanPanjangKainController implements RestfulController<SatuanPanjangKainRestfulModel, SatuanPanjangKain> {

    private SatuanPanjangKainRepository satuanPanjangKainRepository;

    public RestfulSatuanPanjangKainController(SatuanPanjangKainRepository satuanPanjangKainRepository) {
        this.satuanPanjangKainRepository = satuanPanjangKainRepository;
    }

    @Override
    public SatuanPanjangKain findById(Long id){
        SatuanPanjangKain satuanPanjangKain = satuanPanjangKainRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found satuanPanjangKain with id " + id));
        return satuanPanjangKain;
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<SatuanPanjangKainRestfulModel> getSingle(Long id) {
        return ResponseEntity.ok(new SatuanPanjangKainRestfulModel(findById(id)));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<SatuanPanjangKainRestfulModel>> getList() {
        ArrayList<SatuanPanjangKainRestfulModel> satuanPanjangKainRestfulModels = new ArrayList<>();
        satuanPanjangKainRepository.findAll().forEach(satuanPanjangKain -> {
            satuanPanjangKainRestfulModels.add(new SatuanPanjangKainRestfulModel(satuanPanjangKain));
        });
        return ResponseEntity.ok(satuanPanjangKainRestfulModels);
    }

    @Override
    @PostMapping
    public ResponseEntity<SatuanPanjangKainRestfulModel> create(@RequestBody SatuanPanjangKainRestfulModel satuanPanjangKainRestfulModel) {
        return ResponseEntity.ok(new SatuanPanjangKainRestfulModel(satuanPanjangKainRepository.save(satuanPanjangKainRestfulModel.getEntity())));
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<SatuanPanjangKainRestfulModel> update(@PathVariable Long id, @RequestBody SatuanPanjangKainRestfulModel satuanPanjangKainRestfulModel) {
        return ResponseEntity.ok(new SatuanPanjangKainRestfulModel(satuanPanjangKainRepository.save(satuanPanjangKainRestfulModel.getEntityUpdate(findById(id)))));
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(Long id) {
        findById(id);
        satuanPanjangKainRepository.deleteById(id);
        return ResponseEntity.ok("Process with id " + id + " deleted successfully");
    }
}
