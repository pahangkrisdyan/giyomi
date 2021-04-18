package id.giyomi.vms.backend.controller.restful;

import id.giyomi.vms.backend.controller.restful.model.UkuranJumlahRestfulModel;
import id.giyomi.vms.backend.entity.SpkVarian;
import id.giyomi.vms.backend.entity.UkuranJumlah;
import id.giyomi.vms.backend.repository.SpkRepository;
import id.giyomi.vms.backend.repository.SpkVarianRepository;
import id.giyomi.vms.backend.repository.UkuranJumlahRepository;
import id.giyomi.vms.backend.repository.UkuranRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/restful/ukuranJumlahs")
public class RestfulUkuranJumlahController implements RestfulController<UkuranJumlahRestfulModel, UkuranJumlah> {

    private UkuranJumlahRepository ukuranJumlahRepository;
    private UkuranRepository ukuranRepository;
    private SpkVarianRepository spkVarianRepository;

    public RestfulUkuranJumlahController(UkuranJumlahRepository ukuranJumlahRepository, UkuranRepository ukuranRepository, SpkVarianRepository spkVarianRepository) {
        this.ukuranJumlahRepository = ukuranJumlahRepository;
        this.ukuranRepository = ukuranRepository;
        this.spkVarianRepository = spkVarianRepository;
    }

    public UkuranJumlah findById(Long id){
        UkuranJumlah ukuranJumlah = ukuranJumlahRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found ukuranJumlah with id " + id));
        return ukuranJumlah;
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<UkuranJumlahRestfulModel> getSingle(Long id) {
        return ResponseEntity.ok(new UkuranJumlahRestfulModel(findById(id)));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<UkuranJumlahRestfulModel>> getList() {
        ArrayList<UkuranJumlahRestfulModel> ukuranJumlahRestfulModels = new ArrayList<>();
        ukuranJumlahRepository.findAll().forEach(ukuranJumlah -> {
            ukuranJumlahRestfulModels.add(new UkuranJumlahRestfulModel(ukuranJumlah));
        });
        return ResponseEntity.ok(ukuranJumlahRestfulModels);
    }

    @Override
    @PostMapping
    public ResponseEntity<UkuranJumlahRestfulModel> create(@RequestBody UkuranJumlahRestfulModel ukuranJumlahRestfulModel) {
        return ResponseEntity.ok(new UkuranJumlahRestfulModel(ukuranJumlahRepository.save(ukuranJumlahRestfulModel.getEntity(ukuranRepository, spkVarianRepository))));
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<UkuranJumlahRestfulModel> update(@PathVariable Long id, @RequestBody UkuranJumlahRestfulModel ukuranJumlahRestfulModel) {
        return ResponseEntity.ok(new UkuranJumlahRestfulModel(ukuranJumlahRepository.save(ukuranJumlahRestfulModel.getEntityUpdate(findById(id), ukuranRepository, spkVarianRepository))));
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(Long id) {
        findById(id);
        ukuranJumlahRepository.deleteById(id);
        return ResponseEntity.ok("UkuranJumlah with id " + id + " deleted successfully");
    }
}
