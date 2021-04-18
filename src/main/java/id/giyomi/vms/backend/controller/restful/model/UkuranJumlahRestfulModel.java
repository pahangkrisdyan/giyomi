package id.giyomi.vms.backend.controller.restful.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.giyomi.vms.backend.entity.*;
import id.giyomi.vms.backend.repository.SpkProsesRepository;
import id.giyomi.vms.backend.repository.SpkVarianRepository;
import id.giyomi.vms.backend.repository.UkuranRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import javax.persistence.ManyToOne;

public class UkuranJumlahRestfulModel  extends RestfulModel {

    private Long spkVarianId;
    private Long ukuranId;
    private Integer jumlah;

    public UkuranJumlahRestfulModel() {
    }

    public UkuranJumlahRestfulModel(UkuranJumlah ukuranJumlah) {
        super(ukuranJumlah);
        this.spkVarianId = ukuranJumlah.getSpkVarian().getId();
        this.ukuranId = ukuranJumlah.getUkuran().getId();
        this.jumlah = ukuranJumlah.getJumlah();
    }

    public Long getSpkVarianId() {
        return spkVarianId;
    }

    public void setSpkVarianId(Long spkVarianId) {
        this.spkVarianId = spkVarianId;
    }

    public Long getUkuranId() {
        return ukuranId;
    }

    public void setUkuranId(Long ukuranId) {
        this.ukuranId = ukuranId;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    @JsonIgnore
    public UkuranJumlah getEntity(UkuranRepository ukuranRepository, SpkVarianRepository spkVarianRepository){
        Ukuran ukuran = ukuranRepository
                .findById(ukuranId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found ukuran with id " + ukuranId));
        SpkVarian spkVarian = spkVarianRepository
                .findById(spkVarianId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found spkVarian with id " + spkVarianId));
        return new UkuranJumlah(spkVarian,ukuran, jumlah);
    }

    @JsonIgnore
    public UkuranJumlah getEntityUpdate(UkuranJumlah source, UkuranRepository ukuranRepository, SpkVarianRepository spkVarianRepository){
        if(jumlah!=null)source.setJumlah(jumlah);
        if(ukuranId!=null){
            Ukuran ukuran = ukuranRepository
                    .findById(ukuranId)
                    .orElseThrow(()-> new ResourceNotFoundException("Not found ukuran with id " + ukuranId));
            source.setUkuran(ukuran);
        }
        if(spkVarianId!=null){
            SpkVarian spkVarian = spkVarianRepository
                    .findById(spkVarianId)
                    .orElseThrow(()-> new ResourceNotFoundException("Not found spkVarian with id " + spkVarianId));
            source.setSpkVarian(spkVarian);
        }
        return source;
    }
}
