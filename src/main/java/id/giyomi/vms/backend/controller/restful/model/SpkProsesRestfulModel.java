package id.giyomi.vms.backend.controller.restful.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.giyomi.vms.backend.entity.SpkProses;
import id.giyomi.vms.backend.entity.Proses;
import id.giyomi.vms.backend.entity.Spk;
import id.giyomi.vms.backend.entity.SpkProses;
import id.giyomi.vms.backend.repository.ProsesRepository;
import id.giyomi.vms.backend.repository.SpkProsesRepository;
import id.giyomi.vms.backend.repository.SpkRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import javax.persistence.ManyToOne;
import java.util.Date;

public class SpkProsesRestfulModel  extends RestfulModel {

    private Integer durasiRencana;
    private Integer durasiNyata;
    private Date tanggalSelesai;
    private Long prosesId;
    private Integer order;
    private Long spkId;



    public SpkProsesRestfulModel() {
    }

    public SpkProsesRestfulModel(SpkProses spkProses) {
        super(spkProses);
        this.durasiNyata = spkProses.getDurasiNyata();
        this.durasiRencana = spkProses.getDurasiRencana();
        this.tanggalSelesai = spkProses.getTanggalSelesai();
        this.prosesId = spkProses.getProses().getId();
        this.order = spkProses.getOrder();
        this.spkId = spkProses.getSpk().getId();
    }

    public Integer getDurasiRencana() {
        return durasiRencana;
    }

    public void setDurasiRencana(Integer durasiRencana) {
        this.durasiRencana = durasiRencana;
    }

    public Integer getDurasiNyata() {
        return durasiNyata;
    }

    public void setDurasiNyata(Integer durasiNyata) {
        this.durasiNyata = durasiNyata;
    }

    public Date getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(Date tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public Long getProsesId() {
        return prosesId;
    }

    public void setProsesId(Long prosesId) {
        this.prosesId = prosesId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Long getSpkId() {
        return spkId;
    }

    public void setSpkId(Long spkId) {
        this.spkId = spkId;
    }

    @JsonIgnore
    public SpkProses getEntity(SpkRepository spkRepository, ProsesRepository prosesRepository){
        Spk spk = spkRepository
                .findById(spkId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found spk with id " + spkId));
        Proses proses = prosesRepository
                .findById(prosesId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found proses with id " + prosesId));
        return new SpkProses(durasiRencana, durasiNyata, proses, spk, order);
    }

    @JsonIgnore
    public SpkProses getEntityUpdate(SpkProses source, SpkRepository spkRepository, ProsesRepository prosesRepository){
        if(durasiRencana!=null)source.setDurasiRencana(durasiRencana);
        if(durasiNyata!=null)source.setDurasiNyata(durasiNyata);
        if(order!=null)source.setOrder(order);
        if(tanggalSelesai!=null)source.setTanggalSelesai(tanggalSelesai);
        if(spkId!=null){
            Spk spk = spkRepository
                    .findById(spkId)
                    .orElseThrow(()-> new ResourceNotFoundException("Not found spk with id " + spkId));
            source.setSpk(spk);
        }
        if(spkId!=null){
            Proses proses = prosesRepository
                    .findById(prosesId)
                    .orElseThrow(()-> new ResourceNotFoundException("Not found proses with id " + prosesId));
            source.setProses(proses);
        }
        return source;
    }
}
