package id.giyomi.vms.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Status extends AuditModel {

    @Column(unique = true)
    private String nama;

    @JsonIgnore
    @OneToMany( targetEntity=SpkStatus.class, fetch = FetchType.EAGER, cascade = {
            CascadeType.REMOVE
    } )
    @JoinColumn(name = "status_id")
    private Set<SpkStatus> spkStatuses = new HashSet<>();

    public Status() {
    }

    public Status(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Set<SpkStatus> getSpkStatuses() {
        return spkStatuses;
    }

    public void setSpkStatuses(Set<SpkStatus> spkStatuses) {
        this.spkStatuses = spkStatuses;
    }
}
