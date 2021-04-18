package id.giyomi.vms.backend.controller.rest.model.spk.response;

import java.util.HashSet;

public class DetailVarianRes {
    private Double panjangKain;
    private String satuanPanjangKain;
    private String namaVarian;
    private HashSet<DetailUkuranJumlahRes> ukuranJumlahs;

    public DetailVarianRes() {
    }

    public DetailVarianRes(Double panjangKain, String satuanPanjangKain, HashSet<DetailUkuranJumlahRes> ukuranJumlahs, String namaVarian) {
        this.panjangKain = panjangKain;
        this.satuanPanjangKain = satuanPanjangKain;
        this.ukuranJumlahs = ukuranJumlahs;
        this.namaVarian = namaVarian;
    }

    public String getNamaVarian() {
        return namaVarian;
    }

    public void setNamaVarian(String namaVarian) {
        this.namaVarian = namaVarian;
    }

    public Double getPanjangKain() {
        return panjangKain;
    }

    public void setPanjangKain(Double panjangKain) {
        this.panjangKain = panjangKain;
    }

    public String getSatuanPanjangKain() {
        return satuanPanjangKain;
    }

    public void setSatuanPanjangKain(String satuanPanjangKain) {
        this.satuanPanjangKain = satuanPanjangKain;
    }

    public HashSet<DetailUkuranJumlahRes> getUkuranJumlahs() {
        return ukuranJumlahs;
    }

    public void setUkuranJumlahs(HashSet<DetailUkuranJumlahRes> ukuranJumlahs) {
        this.ukuranJumlahs = ukuranJumlahs;
    }
}
