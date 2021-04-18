package id.giyomi.vms.backend.controller.restful.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.giyomi.vms.backend.entity.Catatan;
import id.giyomi.vms.backend.entity.JenisKoleksi;
import id.giyomi.vms.backend.entity.SpkProses;
import id.giyomi.vms.backend.repository.SpkProsesRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class CatatanRestfulModel extends RestfulModel{

    private String isi;
    private Long spkProsesId;

    public CatatanRestfulModel() {
    }

    public CatatanRestfulModel(Catatan catatan) {
        super(catatan);
        this.isi = catatan.getIsi();
        this.spkProsesId = catatan.getSpkProses().getId();
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public Long getSpkProsesId() {
        return spkProsesId;
    }

    public void setSpkProsesId(Long spkProsesId) {
        this.spkProsesId = spkProsesId;
    }

    @JsonIgnore
    public Catatan getEntity(SpkProsesRepository spkProsesRepository){
        SpkProses spkProses = spkProsesRepository
                .findById(spkProsesId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found spkProses with id " + spkProsesId));
        return new Catatan(spkProses, this.isi);
    }

    @JsonIgnore
    public Catatan getEntityUpdate(Catatan source, SpkProsesRepository spkProsesRepository){
        if(isi!=null)source.setIsi(this.getIsi());
        if(spkProsesId!=null){
            SpkProses spkProses = spkProsesRepository
                    .findById(spkProsesId)
                    .orElseThrow(()-> new ResourceNotFoundException("Not found spkpProses with id " + spkProsesId));
            source.setSpkProses(spkProses);
        }
        return source;
    }
}
