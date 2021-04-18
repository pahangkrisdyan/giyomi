package id.giyomi.vms.backend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User extends AuditModel{
    @Column(nullable = false, unique = true)
    private String username;
    private String telepon;
    private String password;
    private String email;
    @Column(name = "foto_downlaod_url")
    private String fotoDownloadUrl;
    @ManyToOne
    private Role role;
    public User(String username, String password,
                String email, String telepon,
                String fotoDownloadUrl, Role role) {
        this.role = role;
        this.username = username;
        this.password = password;
        this.email = email;
        this.telepon = telepon;
        this.fotoDownloadUrl = fotoDownloadUrl;
    }

    public User() {
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFotoDownloadUrl() {
        return fotoDownloadUrl;
    }

    public void setFotoDownloadUrl(String fotoDownloadUrl) {
        this.fotoDownloadUrl = fotoDownloadUrl;
    }
}
