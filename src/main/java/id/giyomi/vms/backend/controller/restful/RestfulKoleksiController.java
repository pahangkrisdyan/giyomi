package id.giyomi.vms.backend.controller.restful;

import id.giyomi.vms.backend.controller.restful.model.KoleksiRestfulModel;
import id.giyomi.vms.backend.entity.JenisKoleksi;
import id.giyomi.vms.backend.entity.Koleksi;
import id.giyomi.vms.backend.repository.JenisKoleksiRepository;
import id.giyomi.vms.backend.repository.KoleksiRepository;
import id.giyomi.vms.backend.repository.KoleksiRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/restful/koleksis")
public class RestfulKoleksiController implements RestfulController<KoleksiRestfulModel, Koleksi> {

    private KoleksiRepository koleksiRepository;
    private JenisKoleksiRepository jenisKoleksiRepository;

    public RestfulKoleksiController(KoleksiRepository koleksiRepository, JenisKoleksiRepository jenisKoleksiRepository) {
        this.koleksiRepository = koleksiRepository;
        this.jenisKoleksiRepository = jenisKoleksiRepository;
    }

    @Override
    public Koleksi findById(Long id){
        Koleksi koleksi = koleksiRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found koleksi with id " + id));
        return koleksi;
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<KoleksiRestfulModel> getSingle(Long id) {
        return ResponseEntity.ok(new KoleksiRestfulModel(findById(id)));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<KoleksiRestfulModel>> getList() {
        ArrayList<KoleksiRestfulModel> koleksiRestfulModels = new ArrayList<>();
        koleksiRepository.findAll().forEach(koleksi -> {
            koleksiRestfulModels.add(new KoleksiRestfulModel(koleksi));
        });
        return ResponseEntity.ok(koleksiRestfulModels);
    }

    @Override
    @PostMapping
    public ResponseEntity<KoleksiRestfulModel> create(@RequestBody KoleksiRestfulModel koleksiRestfulModel) {
        return ResponseEntity.ok(new KoleksiRestfulModel(koleksiRepository.save(koleksiRestfulModel.getEntity(jenisKoleksiRepository))));
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<KoleksiRestfulModel> update(@PathVariable Long id, @RequestBody KoleksiRestfulModel koleksiRestfulModel) {
        return ResponseEntity.ok(new KoleksiRestfulModel(koleksiRepository.save(koleksiRestfulModel.getEntityUpdate(findById(id), jenisKoleksiRepository))));
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(Long id) {
        findById(id);
        koleksiRepository.deleteById(id);
        return ResponseEntity.ok("Koleksi with id " + id + " deleted successfully");
    }
}
