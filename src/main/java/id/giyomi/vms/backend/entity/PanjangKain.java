package id.giyomi.vms.backend.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PanjangKain extends AuditModel {

    private Double panjang;

    @ManyToOne
    private SatuanPanjangKain satuanPanjangKain;

    public PanjangKain() {
    }

    public PanjangKain(Double panjang, SatuanPanjangKain satuanPanjangKain) {
        this.panjang = panjang;
        this.satuanPanjangKain = satuanPanjangKain;
    }

    public Double getPanjang() {
        return panjang;
    }

    public void setPanjang(Double panjang) {
        this.panjang = panjang;
    }

    public SatuanPanjangKain getSatuanPanjangKain() {
        return satuanPanjangKain;
    }

    public void setSatuanPanjangKain(SatuanPanjangKain satuanPanjangKain) {
        this.satuanPanjangKain = satuanPanjangKain;
    }
}
