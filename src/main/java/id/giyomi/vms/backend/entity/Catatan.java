package id.giyomi.vms.backend.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Catatan extends AuditModel {



    @ManyToOne
    private SpkProses spkProses;
    private String isi;

    public Catatan() {
    }

    public Catatan(SpkProses spkProses, String isi) {
        this.isi = isi;
        this.spkProses = spkProses;
    }

    public SpkProses getSpkProses() {
        return spkProses;
    }

    public void setSpkProses(SpkProses spkProses) {
        this.spkProses = spkProses;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }
}
