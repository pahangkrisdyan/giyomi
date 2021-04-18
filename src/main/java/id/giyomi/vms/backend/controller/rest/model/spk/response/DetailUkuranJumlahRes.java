package id.giyomi.vms.backend.controller.rest.model.spk.response;

public class DetailUkuranJumlahRes {
    private Long ukuranJumlahId;
    private String ukuran;
    private Integer jumlah;

    public DetailUkuranJumlahRes() {
    }

    public DetailUkuranJumlahRes(String ukuran, Integer jumlah, Long ukuranJumlahId) {
        this.ukuran = ukuran;
        this.jumlah = jumlah;
        this.ukuranJumlahId = ukuranJumlahId;
    }

    public Long getUkuranJumlahId() {
        return ukuranJumlahId;
    }

    public void setUkuranJumlahId(Long ukuranJumlahId) {
        this.ukuranJumlahId = ukuranJumlahId;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }
}
