package id.giyomi.vms.backend.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class SpkVarian extends AuditModel {

    @OneToOne
    private Spk spk;

    @ManyToOne
    private Varian varian;

    @OneToMany(targetEntity = UkuranJumlah.class, fetch = FetchType.EAGER, cascade = {
            CascadeType.REMOVE
    })
    @JoinColumn(name = "spk_varian_id")
    private Set<UkuranJumlah> ukuranJumlahs = new HashSet<>();

    @ManyToOne
    private PanjangKain panjangKain;

    public SpkVarian() {
    }

    public SpkVarian(Spk spk, Varian varian, PanjangKain panjangKain) {
        this.spk = spk;
        this.varian = varian;
        this.panjangKain = panjangKain;
    }

    public Spk getSpk() {
        return spk;
    }

    public void setSpk(Spk spk) {
        this.spk = spk;
    }

    public Varian getVarian() {
        return varian;
    }

    public void setVarian(Varian varian) {
        this.varian = varian;
    }

    public Set<UkuranJumlah> getUkuranJumlahs() {
        return ukuranJumlahs;
    }

    public void setUkuranJumlahs(Set<UkuranJumlah> ukuranJumlahs) {
        this.ukuranJumlahs = ukuranJumlahs;
    }

    public PanjangKain getPanjangKain() {
        return panjangKain;
    }

    public void setPanjangKain(PanjangKain panjangKain) {
        this.panjangKain = panjangKain;
    }
}
