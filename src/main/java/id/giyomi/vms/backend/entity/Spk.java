package id.giyomi.vms.backend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import id.giyomi.vms.backend.controller.rest.model.SpkStatusEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Spk extends AuditModel {

    @JsonIgnore
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "nilai_spk_id")
    private NilaiSpk nilaiSpk;

    @JsonIgnore
    @ManyToOne
    private Vendor vendor;

    @JsonIgnore
    @OneToMany(
            targetEntity= SpkStatus.class,
            fetch = FetchType.EAGER ,
            cascade = {
                    CascadeType.REMOVE
            }
    )
    @JoinColumn(name = "spk_id")
    private Set<SpkStatus> spkStatuses = new HashSet<>();

    @JsonIgnore
    @OneToMany(
            targetEntity=SpkProses.class,
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.REMOVE
            }
    )
    @JoinColumn(name = "spk_id")
    private Set<SpkProses> spkProseses = new HashSet<>();

    @JsonIgnore
    @OneToMany(
            targetEntity= SpkVarian.class,
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.REMOVE
            }
    )
    @JoinColumn(name = "spk_id")
    private Set<SpkVarian> spkVarians = new HashSet<>();

    public Spk() {
    }

    public Spk(Vendor vendor) {
        this.vendor = vendor;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Set<SpkStatus> getSpkStatuses() {
        return spkStatuses;
    }

    public void setSpkStatuses(Set<SpkStatus> spkStatuses) {
        this.spkStatuses = spkStatuses;
    }

    public Set<SpkProses> getSpkProseses() {
        return spkProseses;
    }

    public void setSpkProseses(Set<SpkProses> spkproseses) {
        this.spkProseses = spkproseses;
    }

    public Set<SpkVarian> getSpkVarians() {
        return spkVarians;
    }

    public void setSpkVarians(Set<SpkVarian> spkVarians) {
        this.spkVarians = spkVarians;
    }

    public NilaiSpk getNilaiSpk() {
        return nilaiSpk;
    }

    public void setNilaiSpk(NilaiSpk nilaiSpk) {
        this.nilaiSpk = nilaiSpk;
    }

    public boolean isCompleted(){
        Status lastStatus = getLastStatus();
        String lastStatusName = lastStatus.getNama().toLowerCase();
        String completedStatusName = SpkStatusEnum.COMPLETED.toString().toLowerCase();
        boolean result = lastStatusName.equals(completedStatusName);
        return result;
    }

    public Status getLastStatus(){
        ArrayList<SpkStatus> spkStatusArrayList = new ArrayList(this.spkStatuses);
        spkStatusArrayList.sort(Comparator.comparing(AuditModel::getCreatedAt));
        return spkStatusArrayList.get(spkStatusArrayList.size()-1).getStatus();
    }
}
