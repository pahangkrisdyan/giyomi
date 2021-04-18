package id.giyomi.vms.backend.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role extends AuditModel{

    @Column(unique = true)
    private String nama;

    @JsonIgnore
    @OneToMany( targetEntity=User.class, fetch = FetchType.EAGER, cascade = {
            CascadeType.REMOVE
    })
    @JoinColumn(name = "role_id")
    private Set<User> users = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "role_hak",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "hak_id")
    )
    private Set<Hak> haks = new HashSet<>();

    public Role() {
    }

    public Role(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Hak> getHaks() {
        return haks;
    }

    public void setHaks(Set<Hak> haks) {
        this.haks = haks;
    }
}

