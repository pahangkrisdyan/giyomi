package id.giyomi.vms.backend.controller.rest.model;


import id.giyomi.vms.backend.entity.User;

public class ResUser {

    private Long id;

    private String username;

    private String email;

    private String telepon;

    private String role;

    private String downloadFotoUrl;

    public ResUser() {
    }

    public ResUser(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.role = user.getRole().getNama();
        this.telepon = user.getTelepon();
        this.username = user.getUsername();
        this.downloadFotoUrl = user.getFotoDownloadUrl();
    }

    public ResUser(Long id, String username, String email, String telepon, String role, String downloadFotoUrl) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.telepon = telepon;
        this.role = role;
        this.downloadFotoUrl = downloadFotoUrl;
    }

    public String getDownloadFotoUrl() {
        return downloadFotoUrl;
    }

    public void setDownloadFotoUrl(String downloadFotoUrl) {
        this.downloadFotoUrl = downloadFotoUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }
}
