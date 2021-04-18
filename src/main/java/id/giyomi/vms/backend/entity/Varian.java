package id.giyomi.vms.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Varian extends AuditModel{

    private String varian;

    @JsonIgnore
    @ManyToOne
    private Koleksi koleksi;

    public Varian(Koleksi koleksi, String varian) {
        this.koleksi = koleksi;
        this.varian = varian;
    }

    public Varian() {
    }

    public String getVarian() {
        return varian;
    }

    public void setVarian(String varian) {
        this.varian = varian;
    }

    public Koleksi getKoleksi() {
        return koleksi;
    }

    public void setKoleksi(Koleksi koleksi) {
        this.koleksi = koleksi;
    }
}
