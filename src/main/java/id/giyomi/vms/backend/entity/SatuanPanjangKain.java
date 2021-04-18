package id.giyomi.vms.backend.entity;

import javax.persistence.Entity;

@Entity
public class SatuanPanjangKain extends AuditModel{
    private String nama;

    public SatuanPanjangKain() {}

    public SatuanPanjangKain(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
