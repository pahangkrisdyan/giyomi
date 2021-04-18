package id.giyomi.vms.backend.controller.restful;

import id.giyomi.vms.backend.controller.restful.model.StatusRestfulModel;
import id.giyomi.vms.backend.controller.restful.model.StatusRestfulModel;
import id.giyomi.vms.backend.entity.Status;
import id.giyomi.vms.backend.entity.Status;
import id.giyomi.vms.backend.repository.StatusRepository;
import id.giyomi.vms.backend.repository.SpkProsesRepository;
import id.giyomi.vms.backend.repository.StatusRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/restful/statuses")
public class RestfulStatusController implements RestfulController<StatusRestfulModel, Status> {

    private StatusRepository statusRepository;

    public RestfulStatusController(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Status findById(Long id){
        Status status = statusRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found status with id " + id));
        return status;
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<StatusRestfulModel> getSingle(Long id) {
        return ResponseEntity.ok(new StatusRestfulModel(findById(id)));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<StatusRestfulModel>> getList() {
        ArrayList<StatusRestfulModel> statusRestfulModels = new ArrayList<>();
        statusRepository.findAll().forEach(status -> {
            statusRestfulModels.add(new StatusRestfulModel(status));
        });
        return ResponseEntity.ok(statusRestfulModels);
    }

    @Override
    @PostMapping
    public ResponseEntity<StatusRestfulModel> create(@RequestBody StatusRestfulModel statusRestfulModel) {
        return ResponseEntity.ok(new StatusRestfulModel(statusRepository.save(statusRestfulModel.getEntity())));
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<StatusRestfulModel> update(@PathVariable Long id, @RequestBody StatusRestfulModel statusRestfulModel) {
        return ResponseEntity.ok(new StatusRestfulModel(statusRepository.save(statusRestfulModel.getEntityUpdate(findById(id)))));
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(Long id) {
        findById(id);
        statusRepository.deleteById(id);
        return ResponseEntity.ok("Status with id " + id + " deleted successfully");
    }

}
