package id.giyomi.vms.backend.controller.restful.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.giyomi.vms.backend.entity.Spk;
import id.giyomi.vms.backend.entity.SpkStatus;
import id.giyomi.vms.backend.entity.SpkProses;
import id.giyomi.vms.backend.entity.Status;
import id.giyomi.vms.backend.repository.SpkProsesRepository;
import id.giyomi.vms.backend.repository.SpkRepository;
import id.giyomi.vms.backend.repository.StatusRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class SpkStatusRestfulModel  extends RestfulModel{

    private Long spkId;
    private Long statusId;

    public SpkStatusRestfulModel() {
    }

    public SpkStatusRestfulModel(SpkStatus spkStatus) {
        super(spkStatus);
        this.spkId = spkStatus.getSpk().getId();
        this.statusId = spkStatus.getStatus().getId();
    }

    public Long getSpkId() {
        return spkId;
    }

    public void setSpkId(Long spkId) {
        this.spkId = spkId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    @JsonIgnore
    public SpkStatus getEntity(SpkRepository spkRepository, StatusRepository statusRepository){
        Spk spk = spkRepository
                .findById(spkId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found spk with id " + spkId));
        Status status = statusRepository
                .findById(statusId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found status with id " + statusId));

        return new SpkStatus(status, spk);
    }

    @JsonIgnore
    public SpkStatus getEntityUpdate(SpkStatus source, SpkRepository spkRepository, StatusRepository statusRepository){
        if(spkId!=null){
            Spk spk = spkRepository
                    .findById(spkId)
                    .orElseThrow(()-> new ResourceNotFoundException("Not found spk with id " + spkId));
            source.setSpk(spk);
        }
        if(statusId!=null){
            Status status = statusRepository
                    .findById(statusId)
                    .orElseThrow(()-> new ResourceNotFoundException("Not found status with id " + statusId));
            source.setStatus(status);
        }
        return source;
    }
}
