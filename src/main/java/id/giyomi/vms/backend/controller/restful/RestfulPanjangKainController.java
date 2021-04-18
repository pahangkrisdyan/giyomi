package id.giyomi.vms.backend.controller.restful;

import id.giyomi.vms.backend.controller.restful.model.CatatanRestfulModel;
import id.giyomi.vms.backend.controller.restful.model.PanjangKainRestfulModel;
import id.giyomi.vms.backend.entity.Catatan;
import id.giyomi.vms.backend.entity.PanjangKain;
import id.giyomi.vms.backend.entity.SatuanPanjangKain;
import id.giyomi.vms.backend.repository.CatatanRepository;
import id.giyomi.vms.backend.repository.PanjangKainRepository;
import id.giyomi.vms.backend.repository.SatuanPanjangKainRepository;
import id.giyomi.vms.backend.repository.SpkProsesRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/restful/panjangKains")
public class RestfulPanjangKainController implements RestfulController<PanjangKainRestfulModel, PanjangKain> {

    private PanjangKainRepository panjangKainRepository;
    private SatuanPanjangKainRepository satuanPanjangKainRepository;

    public RestfulPanjangKainController(PanjangKainRepository panjangKainRepository, SatuanPanjangKainRepository satuanPanjangKainRepository) {
        this.panjangKainRepository = panjangKainRepository;
        this.satuanPanjangKainRepository = satuanPanjangKainRepository;
    }

    @Override
    public PanjangKain findById(Long id){
        PanjangKain panjangKain = panjangKainRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found panjangKain with id" + id));
        return panjangKain;
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<PanjangKainRestfulModel> getSingle(Long id) {
        return ResponseEntity.ok(new PanjangKainRestfulModel(findById(id)));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<PanjangKainRestfulModel>> getList() {
        ArrayList<PanjangKainRestfulModel> panjangKainRestfulModels = new ArrayList<>();
        panjangKainRepository.findAll().forEach(panjangKain -> {
            panjangKainRestfulModels.add(new PanjangKainRestfulModel(panjangKain));
        });
        return ResponseEntity.ok(panjangKainRestfulModels);
    }

    @Override
    @PostMapping
    public ResponseEntity<PanjangKainRestfulModel> create(@RequestBody PanjangKainRestfulModel panjangKainRestfulModel) {
        return ResponseEntity.ok(new PanjangKainRestfulModel(panjangKainRepository.save(panjangKainRestfulModel.getEntity(satuanPanjangKainRepository))));
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<PanjangKainRestfulModel> update(@PathVariable Long id, @RequestBody PanjangKainRestfulModel panjangKainRestfulModel) {
        return ResponseEntity.ok(new PanjangKainRestfulModel(panjangKainRepository.save(panjangKainRestfulModel.getEntityUpdate(findById(id), satuanPanjangKainRepository))));
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(Long id) {
        findById(id);
        satuanPanjangKainRepository.deleteById(id);
        return ResponseEntity.ok("PanjangKain with id " + id + " deleted successfully");
    }

}
