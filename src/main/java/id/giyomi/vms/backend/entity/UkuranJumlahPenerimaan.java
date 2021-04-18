package id.giyomi.vms.backend.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class UkuranJumlahPenerimaan extends AuditModel {

    @ManyToOne
    private UkuranJumlah ukuranJumlah;

    private Integer jumlahPenerimaan;

    private Boolean isDiterima;

    public UkuranJumlahPenerimaan() {
    }

    public UkuranJumlahPenerimaan(UkuranJumlah ukuranJumlah, Integer jumlahPenerimaan, Boolean isDiterima) {
        this.ukuranJumlah = ukuranJumlah;
        this.jumlahPenerimaan = jumlahPenerimaan;
        this.isDiterima = isDiterima;
    }

    public UkuranJumlah getUkuranJumlah() {
        return ukuranJumlah;
    }

    public void setUkuranJumlah(UkuranJumlah ukuranJumlah) {
        this.ukuranJumlah = ukuranJumlah;
    }

    public Integer getJumlahPenerimaan() {
        return jumlahPenerimaan;
    }

    public void setJumlahPenerimaan(Integer jumlahDiTerima) {
        this.jumlahPenerimaan = jumlahDiTerima;
    }

    public Boolean getDiterima() {
        return isDiterima;
    }

    public void setDiterima(Boolean diterima) {
        isDiterima = diterima;
    }
}
