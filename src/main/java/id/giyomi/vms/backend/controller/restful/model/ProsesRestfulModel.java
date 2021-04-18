package id.giyomi.vms.backend.controller.restful.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.giyomi.vms.backend.entity.Proses;
import id.giyomi.vms.backend.entity.SpkProses;
import id.giyomi.vms.backend.repository.SpkProsesRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class ProsesRestfulModel  extends RestfulModel{
    private String nama;

    public ProsesRestfulModel() {
    }

    public ProsesRestfulModel(Proses proses) {
        super(proses);
        this.nama = proses.getNama();
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @JsonIgnore
    public Proses getEntity(){
        return new Proses(nama);
    }

    @JsonIgnore
    public Proses getEntityUpdate(Proses source){
        if(nama!=null)source.setNama(this.getNama());
        return source;
    }
}
