package id.giyomi.vms.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Koleksi extends AuditModel {

    private String nama;

    private String fotoDownloadUrl;

    private String detailAksesoris;

    @ManyToOne
    private JenisKoleksi jenisKoleksi;

    @JsonIgnore
    @OneToMany( targetEntity= Varian.class, fetch = FetchType.EAGER, cascade = {
            CascadeType.REMOVE
    })
    @JoinColumn(name = "koleksi_id")
    private Set<Varian> varians = new HashSet<>();

    public Koleksi(String nama, String fotoDownloadUrl,  JenisKoleksi jenisKoleksi, String detailAksesoris) {
        this.nama = nama;
        this.jenisKoleksi = jenisKoleksi;
        this.fotoDownloadUrl = fotoDownloadUrl;
        this.detailAksesoris = detailAksesoris;
    }

    public Koleksi() {
    }

    public String getDetailAksesoris() {
        return detailAksesoris;
    }

    public void setDetailAksesoris(String detailAksesoris) {
        this.detailAksesoris = detailAksesoris;
    }

    public Set<Varian> getVarians() {
        return varians;
    }

    public void setVarians(Set<Varian> varians) {
        this.varians = varians;
    }

    public JenisKoleksi getJenisKoleksi() {
        return jenisKoleksi;
    }

    public void setJenisKoleksi(JenisKoleksi jenisKoleksi) {
        this.jenisKoleksi = jenisKoleksi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getFotoDownloadUrl() {
        return fotoDownloadUrl;
    }

    public void setFotoDownloadUrl(String fotoDownloadUrl) {
        this.fotoDownloadUrl = fotoDownloadUrl;
    }
}
