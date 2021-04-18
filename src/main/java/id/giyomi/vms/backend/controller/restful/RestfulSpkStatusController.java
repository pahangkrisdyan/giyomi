package id.giyomi.vms.backend.controller.restful;

import id.giyomi.vms.backend.controller.rest.model.SpkStatusEnum;
import id.giyomi.vms.backend.controller.restful.model.SpkStatusRestfulModel;
import id.giyomi.vms.backend.entity.AuditModel;
import id.giyomi.vms.backend.entity.SpkStatus;
import id.giyomi.vms.backend.entity.SpkStatus;
import id.giyomi.vms.backend.entity.Status;
import id.giyomi.vms.backend.repository.*;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/restful/spkStatuses")
public class RestfulSpkStatusController implements RestfulController<SpkStatusRestfulModel, SpkStatus> {

    private SpkStatusRepository spkStatusRepository;
    private SpkRepository spkRepository;
    private StatusRepository statusRepository;

    public RestfulSpkStatusController(SpkStatusRepository spkStatusRepository, SpkRepository spkRepository, StatusRepository statusRepository) {
        this.spkStatusRepository = spkStatusRepository;
        this.spkRepository = spkRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public SpkStatus findById(Long id){
        SpkStatus spkStatus = spkStatusRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found spkStatus with id " + id));
        return spkStatus;
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<SpkStatusRestfulModel> getSingle(Long id) {
        return ResponseEntity.ok(new SpkStatusRestfulModel(findById(id)));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<SpkStatusRestfulModel>> getList() {
        ArrayList<SpkStatusRestfulModel> spkStatusRestfulModels = new ArrayList<>();
        spkStatusRepository.findAll().forEach(spkStatus -> {
            spkStatusRestfulModels.add(new SpkStatusRestfulModel(spkStatus));
        });
        return ResponseEntity.ok(spkStatusRestfulModels);
    }

    @Override
    @PostMapping
    public ResponseEntity<SpkStatusRestfulModel> create(@RequestBody SpkStatusRestfulModel spkStatusRestfulModel) {
//        Status reqStatus = statusRepository
//                .findById(spkStatusRestfulModel.getStatusId())
//                .orElseThrow(()->new ResourceNotFoundException("Not found status with id " + spkStatusRestfulModel.getStatusId()));
//        List<SpkStatus> spkStatuses = spkStatusRepository.findBySpkId(spkStatusRestfulModel.getSpkId());
//        spkStatuses.sort(Comparator.comparing(AuditModel::getCreatedAt));
//        SpkStatus lastStatus = spkStatuses.get(spkStatuses.size()-1);
//        if(reqStatus.getNama().equals(SpkStatusEnum.ON_GOING)&&!lastStatus.getStatus().getNama().equals(SpkStatusEnum.REQ_VAL)){
//            throw new UnsupportedOperationException("SPK does not on validation request status yet, change SPK status to be request validation first!");
//        }
        return ResponseEntity.ok(new SpkStatusRestfulModel(spkStatusRepository.save(spkStatusRestfulModel.getEntity(spkRepository, statusRepository))));
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<SpkStatusRestfulModel> update(@PathVariable Long id, @RequestBody SpkStatusRestfulModel spkStatusRestfulModel) {
        return ResponseEntity.ok(new SpkStatusRestfulModel(spkStatusRepository.save(spkStatusRestfulModel.getEntityUpdate(findById(id), spkRepository, statusRepository))));
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(Long id) {
        spkStatusRepository.deleteById(id);
        return ResponseEntity.ok("SpkStatus with id " + id + " deleted successfully");
    }
}
