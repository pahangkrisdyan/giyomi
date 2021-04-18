package id.giyomi.vms.backend.controller.rest.model.spk.request;

import java.util.ArrayList;

public class DetailVarianReq {
    private Long varianId;
    private ArrayList<DetailUkuranJumlahReq> ukuranJumlahs;
    private Double panjangKain;
    private Long satuanPanjangkainId;

    public DetailVarianReq() {
    }

    public DetailVarianReq(Long varianId, ArrayList<DetailUkuranJumlahReq> ukuranJumlahs, Double panjangKain, Long satuanPanjangkainId) {
        this.varianId = varianId;
        this.ukuranJumlahs = ukuranJumlahs;
        this.panjangKain = panjangKain;
        this.satuanPanjangkainId = satuanPanjangkainId;
    }

    public Long getVarianId() {
        return varianId;
    }

    public void setVarianId(Long varianId) {
        this.varianId = varianId;
    }

    public ArrayList<DetailUkuranJumlahReq> getUkuranJumlahs() {
        return ukuranJumlahs;
    }

    public void setUkuranJumlahs(ArrayList<DetailUkuranJumlahReq> ukuranJumlahs) {
        this.ukuranJumlahs = ukuranJumlahs;
    }

    public Double getPanjangKain() {
        return panjangKain;
    }

    public void setPanjangKain(Double panjangKain) {
        this.panjangKain = panjangKain;
    }

    public Long getSatuanPanjangkainId() {
        return satuanPanjangkainId;
    }

    public void setSatuanPanjangkainId(Long satuanPanjangkainId) {
        this.satuanPanjangkainId = satuanPanjangkainId;
    }
}
