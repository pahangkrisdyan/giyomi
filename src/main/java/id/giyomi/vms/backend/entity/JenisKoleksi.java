package id.giyomi.vms.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class JenisKoleksi extends AuditModel {

    private String nama;

    @JsonIgnore
    @OneToMany(targetEntity = Koleksi.class, fetch = FetchType.EAGER, cascade = {
            CascadeType.REMOVE
    })
    @JoinColumn(name = "jenis_koleksi_id")
    private Set<Koleksi> koleksis = new HashSet<>();

    public JenisKoleksi(String nama) {
        this.nama = nama;
    }

    public JenisKoleksi() {
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Set<Koleksi> getKoleksis() {
        return koleksis;
    }

    public void setKoleksis(Set<Koleksi> koleksis) {
        this.koleksis = koleksis;
    }
}
