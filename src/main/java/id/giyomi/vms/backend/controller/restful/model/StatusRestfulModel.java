package id.giyomi.vms.backend.controller.restful.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.giyomi.vms.backend.entity.Status;
import id.giyomi.vms.backend.entity.SpkProses;
import id.giyomi.vms.backend.repository.SpkProsesRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class StatusRestfulModel  extends RestfulModel{

    private String nama;

    public StatusRestfulModel() {
    }

    public StatusRestfulModel(Status status) {
        super(status);
        this.nama = status.getNama();
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @JsonIgnore
    public Status getEntity(){
        return new Status(this.nama);
    }

    @JsonIgnore
    public Status getEntityUpdate(Status source){
        if(nama!=null)source.setNama(nama);
        return source;
    }
}
