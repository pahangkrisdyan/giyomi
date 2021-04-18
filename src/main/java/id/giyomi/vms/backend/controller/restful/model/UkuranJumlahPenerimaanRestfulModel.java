package id.giyomi.vms.backend.controller.restful.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.giyomi.vms.backend.entity.UkuranJumlah;
import id.giyomi.vms.backend.entity.UkuranJumlahPenerimaan;
import id.giyomi.vms.backend.repository.UkuranJumlahRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class UkuranJumlahPenerimaanRestfulModel extends RestfulModel {

    private Long ukuranJumlahId;
    private Integer jumlahDiterima;
    private Boolean isDiterima;

    public UkuranJumlahPenerimaanRestfulModel() {
    }

    public UkuranJumlahPenerimaanRestfulModel(Long ukuranJumlahId, Integer jumlahDiterima, boolean isDiterima) {
        this.isDiterima = isDiterima;
        this.jumlahDiterima = jumlahDiterima;
        this.ukuranJumlahId = ukuranJumlahId;
    }

    public UkuranJumlahPenerimaanRestfulModel(UkuranJumlahPenerimaan ukuranJumlahPenerimaan) {
        super(ukuranJumlahPenerimaan);
        this.jumlahDiterima = ukuranJumlahPenerimaan.getJumlahPenerimaan();
        this.ukuranJumlahId = ukuranJumlahPenerimaan.getUkuranJumlah().getId();
        this.isDiterima = ukuranJumlahPenerimaan.getDiterima();
    }

    public Long getUkuranJumlahId() {
        return ukuranJumlahId;
    }

    public void setUkuranJumlahId(Long ukuranJumlahId) {
        this.ukuranJumlahId = ukuranJumlahId;
    }

    public Integer getJumlahDiterima() {
        return jumlahDiterima;
    }

    public void setJumlahDiterima(Integer jumlahDiterima) {
        this.jumlahDiterima = jumlahDiterima;
    }

    public Boolean getDiterima() {
        return isDiterima;
    }

    public void setDiterima(Boolean diterima) {
        isDiterima = diterima;
    }

    @JsonIgnore
    public UkuranJumlahPenerimaan getEntity(UkuranJumlahRepository ukuranJumlahRepository){
        UkuranJumlah ukuranJumlah = ukuranJumlahRepository
                .findById(ukuranJumlahId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found ukuranJumlah with id " + ukuranJumlahId));
        return new UkuranJumlahPenerimaan(ukuranJumlah, jumlahDiterima, isDiterima);
    }

    @JsonIgnore
    public UkuranJumlahPenerimaan getEntityUpdate(UkuranJumlahPenerimaan source, UkuranJumlahRepository ukuranJumlahRepository){
        if(jumlahDiterima!=null)source.setJumlahPenerimaan(jumlahDiterima);
        if(isDiterima!=null)source.setDiterima(isDiterima);
        if(ukuranJumlahId!=null){
            UkuranJumlah ukuranJumlah = ukuranJumlahRepository
                    .findById(ukuranJumlahId)
                    .orElseThrow(()-> new ResourceNotFoundException("Not found ukuranJumlah with id " + ukuranJumlahId));
            source.setUkuranJumlah(ukuranJumlah);
        }
        return source;
    }
}
