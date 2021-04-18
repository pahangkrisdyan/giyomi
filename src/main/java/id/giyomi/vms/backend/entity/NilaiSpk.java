package id.giyomi.vms.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class NilaiSpk extends AuditModel {

    @JsonIgnore
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "spk_id")
    private Spk spk;

    private Double kerapihan;
    private Double kualitas;
    private Double waktu;
    private Double jumlah;

    private String komentar;

    public NilaiSpk() {
    }

    public NilaiSpk(Spk spk, Double kerapihan, Double kualitas, Double waktu, Double jumlah, String komentar) {
        this.spk = spk;
        this.kerapihan = kerapihan;
        this.kualitas = kualitas;
        this.waktu = waktu;
        this.jumlah = jumlah;
        this.komentar = komentar;
    }

    public Double getRata(){
        return (kerapihan+kualitas+waktu+jumlah)/4;
    }

    public Spk getSpk() {
        return spk;
    }

    public void setSpk(Spk spk) {
        this.spk = spk;
    }

    public Double getKerapihan() {
        return kerapihan;
    }

    public void setKerapihan(Double kerapihan) {
        this.kerapihan = kerapihan;
    }

    public Double getKualitas() {
        return kualitas;
    }

    public void setKualitas(Double kualitas) {
        this.kualitas = kualitas;
    }

    public Double getWaktu() {
        return waktu;
    }

    public void setWaktu(Double waktu) {
        this.waktu = waktu;
    }

    public Double getJumlah() {
        return jumlah;
    }

    public void setJumlah(Double jumlah) {
        this.jumlah = jumlah;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }
}
