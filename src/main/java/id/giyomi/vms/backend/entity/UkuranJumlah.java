package id.giyomi.vms.backend.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UkuranJumlah extends AuditModel {

    @ManyToOne
    private SpkVarian spkVarian;

    @ManyToOne
    private Ukuran ukuran;

    private Integer jumlah;

    @OneToMany( targetEntity= UkuranJumlahPenerimaan.class, fetch = FetchType.EAGER, cascade = {
            CascadeType.REMOVE
    } )
    @JoinColumn(name = "ukuran_jumlah_id")
    private Set<UkuranJumlahPenerimaan> ukuranJumlahPenerimaans = new HashSet<>();

    public UkuranJumlah() {
    }

    public UkuranJumlah(SpkVarian spkVarian, Ukuran ukuran, Integer jumlah) {
        this.spkVarian = spkVarian;
        this.ukuran = ukuran;
        this.jumlah = jumlah;
    }

    public SpkVarian getSpkVarian() {
        return spkVarian;
    }

    public void setSpkVarian(SpkVarian spkVarian) {
        this.spkVarian = spkVarian;
    }

    public Ukuran getUkuran() {
        return ukuran;
    }

    public void setUkuran(Ukuran ukuran) {
        this.ukuran = ukuran;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Set<UkuranJumlahPenerimaan> getUkuranJumlahPenerimaans() {
        return ukuranJumlahPenerimaans;
    }

    public void setUkuranJumlahPenerimaans(Set<UkuranJumlahPenerimaan> ukuranJumlahPenerimaans) {
        this.ukuranJumlahPenerimaans = ukuranJumlahPenerimaans;
    }
}
