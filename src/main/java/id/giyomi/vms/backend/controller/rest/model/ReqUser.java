package id.giyomi.vms.backend.controller.rest.model;

import id.giyomi.vms.backend.entity.Role;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

public class ReqUser {
    private String username;
    private String telepon;
    private String password;
    private String email;
    private String fotoDownloadUrl;
    private Long roleId;

    public ReqUser(String username, String telepon, String password, String email, String fotoDownloadUrl, Long roleId) {
        this.username = username;
        this.telepon = telepon;
        this.password = password;
        this.email = email;
        this.fotoDownloadUrl = fotoDownloadUrl;
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
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

    public String getFotoDownloadUrl() {
        return fotoDownloadUrl;
    }

    public void setFotoDownloadUrl(String fotoDownloadUrl) {
        this.fotoDownloadUrl = fotoDownloadUrl;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
