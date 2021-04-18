package id.giyomi.vms.backend.controller.restful;

import id.giyomi.vms.backend.controller.restful.model.JenisKoleksiRestfulModel;
import id.giyomi.vms.backend.entity.JenisKoleksi;
import id.giyomi.vms.backend.repository.JenisKoleksiRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/restful/jenisKoleksis")
public class RestfulJenisKoleksiController implements RestfulController<JenisKoleksiRestfulModel, JenisKoleksi> {

    private JenisKoleksiRepository jenisKoleksiRepository;

    public RestfulJenisKoleksiController(JenisKoleksiRepository jenisKoleksiRepository) {
        this.jenisKoleksiRepository = jenisKoleksiRepository;
    }

    @Override
    public JenisKoleksi findById(Long id){
        JenisKoleksi jenisKoleksi = jenisKoleksiRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found jenisKoleksi with id " + id));
        return jenisKoleksi;
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<JenisKoleksiRestfulModel> getSingle(Long id) {
        return ResponseEntity.ok(new JenisKoleksiRestfulModel(findById(id)));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<JenisKoleksiRestfulModel>> getList() {
        ArrayList<JenisKoleksiRestfulModel> jenisKoleksiRestfulModels = new ArrayList<>();
        jenisKoleksiRepository.findAll().forEach(jenisKoleksi -> {
            jenisKoleksiRestfulModels.add(new JenisKoleksiRestfulModel(jenisKoleksi));
        });
        return ResponseEntity.ok(jenisKoleksiRestfulModels);
    }

    @Override
    @PostMapping
    public ResponseEntity<JenisKoleksiRestfulModel> create(@RequestBody JenisKoleksiRestfulModel jenisKoleksiRestfulModel) {
        return ResponseEntity.ok(new JenisKoleksiRestfulModel(jenisKoleksiRepository.save(jenisKoleksiRestfulModel.getEntity())));
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<JenisKoleksiRestfulModel> update(@PathVariable Long id, @RequestBody JenisKoleksiRestfulModel jenisKoleksiRestfulModel) {
        return ResponseEntity.ok(new JenisKoleksiRestfulModel(jenisKoleksiRepository.save(jenisKoleksiRestfulModel.getEntityUpdate(findById(id)))));
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(Long id) {
        findById(id);
        jenisKoleksiRepository.deleteById(id);
        return ResponseEntity.ok("JenisKoleksi with id " + id + " deleted successfully");
    }
}
