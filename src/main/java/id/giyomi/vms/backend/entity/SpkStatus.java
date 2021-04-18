package id.giyomi.vms.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class SpkStatus extends AuditModel  implements Comparable<SpkStatus>{

    @ManyToOne
    private Status status;

    @JsonIgnore
    @ManyToOne
    private Spk spk;

    public SpkStatus() {
    }

    public SpkStatus(Status status, Spk spk) {
        this.status = status;
        this.spk = spk;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Spk getSpk() {
        return spk;
    }

    public void setSpk(Spk spk) {
        this.spk = spk;
    }

    @Override
    public int compareTo(SpkStatus o) {
        return this.getCreatedAt().compareTo(o.getCreatedAt());
    }
}
