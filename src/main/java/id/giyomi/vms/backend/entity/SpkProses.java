package id.giyomi.vms.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class SpkProses extends AuditModel {
    private Integer durasiRencana;

    private Integer durasiNyata;

    private Date tanggalSelesai;

    @ManyToOne
    private Proses proses;

    @JsonIgnore
    private Integer order1;

    @JsonIgnore
    @ManyToOne
    private Spk spk;

    @JsonIgnore
    @OneToMany( targetEntity= Catatan.class, fetch = FetchType.EAGER , cascade = {
            CascadeType.REMOVE
    })
    @JoinColumn(name = "spk_proses_id")
    private Set<Catatan> catatans = new HashSet<>();

    public SpkProses(Integer durasiRencana, Integer durasiNyata, Proses proses, Spk spk, Integer order) {
        this.durasiRencana = durasiRencana;
        this.durasiNyata = durasiNyata;
        this.proses = proses;
        this.spk = spk;
        this.order1 = order;
    }

    public SpkProses() {
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

    public Proses getProses() {
        return proses;
    }

    public void setProses(Proses proses) {
        this.proses = proses;
    }

    public Spk getSpk() {
        return spk;
    }

    public void setSpk(Spk spk) {
        this.spk = spk;
    }

    public Set<Catatan> getCatatans() {
        return catatans;
    }

    public void setCatatans(Set<Catatan> catatans) {
        this.catatans = catatans;
    }

    public Integer getOrder() {
        return order1;
    }

    public void setOrder(Integer order) {
        this.order1 = order;
    }

    public Date getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(Date tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }
}
