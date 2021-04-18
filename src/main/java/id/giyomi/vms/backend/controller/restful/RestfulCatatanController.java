package id.giyomi.vms.backend.controller.restful;

import id.giyomi.vms.backend.controller.restful.model.CatatanRestfulModel;
import id.giyomi.vms.backend.controller.restful.model.JenisKoleksiRestfulModel;
import id.giyomi.vms.backend.entity.Catatan;
import id.giyomi.vms.backend.entity.JenisKoleksi;
import id.giyomi.vms.backend.repository.CatatanRepository;
import id.giyomi.vms.backend.repository.JenisKoleksiRepository;
import id.giyomi.vms.backend.repository.SpkProsesRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/restful/catatans")
public class RestfulCatatanController
        implements RestfulController<CatatanRestfulModel, Catatan> {

    private CatatanRepository catatanRepository;
    private SpkProsesRepository spkProsesRepository;

    public RestfulCatatanController(CatatanRepository catatanRepository,
                            SpkProsesRepository spkProsesRepository) {
        this.catatanRepository = catatanRepository;
        this.spkProsesRepository = spkProsesRepository;
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<CatatanRestfulModel> getSingle(Long id) {
        return ResponseEntity.ok(new CatatanRestfulModel(findById(id)));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<CatatanRestfulModel>> getList() {
        ArrayList<CatatanRestfulModel> catatanRestfulModels = new ArrayList<>();
        catatanRepository.findAll().forEach(catatan -> {
            catatanRestfulModels.add(new CatatanRestfulModel(catatan));
        });
        return ResponseEntity.ok(catatanRestfulModels);
    }

    @Override
    @PostMapping
    public ResponseEntity<CatatanRestfulModel> create(@RequestBody CatatanRestfulModel catatanRestfulModel) {
        return ResponseEntity.ok(new CatatanRestfulModel(catatanRepository.save(catatanRestfulModel.getEntity(spkProsesRepository))));
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<CatatanRestfulModel> update(@PathVariable Long id, @RequestBody CatatanRestfulModel catatanRestfulModel) {
        return ResponseEntity.ok(new CatatanRestfulModel(catatanRepository.save(catatanRestfulModel.getEntityUpdate(findById(id), spkProsesRepository))));
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(Long id) {
        findById(id);
        catatanRepository.deleteById(id);
        return ResponseEntity.ok("Catatan with id " + id + " deleted successfully");
    }

    @Override
    public Catatan findById(Long id){
        Catatan catatan = catatanRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found catatan with id " + id));
        return catatan;
    }
}
