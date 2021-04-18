package id.giyomi.vms.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Ukuran extends AuditModel {

    @Column(unique = true)
    private String nama;

    public Ukuran(String nama) {
        this.nama = nama;
    }

    public Ukuran() {
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

}
