package id.giyomi.vms.backend.controller.restful;

import id.giyomi.vms.backend.controller.restful.model.UkuranJumlahPenerimaanRestfulModel;
import id.giyomi.vms.backend.entity.UkuranJumlahPenerimaan;
import id.giyomi.vms.backend.repository.UkuranJumlahPenerimaanRepository;
import id.giyomi.vms.backend.repository.UkuranJumlahRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/restful/ukuranJumlahDiterimas")
public class RestfulUkuranJumlahPenerimaanController implements RestfulController<UkuranJumlahPenerimaanRestfulModel, UkuranJumlahPenerimaan> {

    private UkuranJumlahPenerimaanRepository ukuranJumlahPenerimaanRepository;
    private UkuranJumlahRepository ukuranJumlahRepository;

    public RestfulUkuranJumlahPenerimaanController(UkuranJumlahPenerimaanRepository ukuranJumlahPenerimaanRepository, UkuranJumlahRepository ukuranJumlahRepository) {
        this.ukuranJumlahPenerimaanRepository = ukuranJumlahPenerimaanRepository;
        this.ukuranJumlahRepository = ukuranJumlahRepository;
    }

    public UkuranJumlahPenerimaan findById(Long id){
        UkuranJumlahPenerimaan ukuranJumlahPenerimaan = ukuranJumlahPenerimaanRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found ukuranJumlahPenerimaan with id " + id));
        return ukuranJumlahPenerimaan;
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<UkuranJumlahPenerimaanRestfulModel> getSingle(Long id) {
        return ResponseEntity.ok(new UkuranJumlahPenerimaanRestfulModel(findById(id)));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<UkuranJumlahPenerimaanRestfulModel>> getList() {
        ArrayList<UkuranJumlahPenerimaanRestfulModel> ukuranJumlahPenerimaanRestfulModels = new ArrayList<>();
        ukuranJumlahPenerimaanRepository.findAll().forEach(ukuranJumlahPenerimaan -> {
            ukuranJumlahPenerimaanRestfulModels.add(new UkuranJumlahPenerimaanRestfulModel(ukuranJumlahPenerimaan));
        });
        return ResponseEntity.ok(ukuranJumlahPenerimaanRestfulModels);
    }

    @Override
    @PostMapping
    public ResponseEntity<UkuranJumlahPenerimaanRestfulModel> create(@RequestBody UkuranJumlahPenerimaanRestfulModel ukuranJumlahPenerimaanRestfulModel) {
        return ResponseEntity.ok(new UkuranJumlahPenerimaanRestfulModel(ukuranJumlahPenerimaanRepository.save(ukuranJumlahPenerimaanRestfulModel.getEntity(ukuranJumlahRepository))));
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<UkuranJumlahPenerimaanRestfulModel> update(@PathVariable Long id, @RequestBody UkuranJumlahPenerimaanRestfulModel ukuranJumlahPenerimaanRestfulModel) {
        return ResponseEntity.ok(new UkuranJumlahPenerimaanRestfulModel(ukuranJumlahPenerimaanRepository.save(ukuranJumlahPenerimaanRestfulModel.getEntityUpdate(findById(id), ukuranJumlahRepository))));
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(Long id) {
        findById(id);
        ukuranJumlahPenerimaanRepository.deleteById(id);
        return ResponseEntity.ok("UkuranJumlahPenerimaan with id " + id + " deleted successfully");
    }
}
