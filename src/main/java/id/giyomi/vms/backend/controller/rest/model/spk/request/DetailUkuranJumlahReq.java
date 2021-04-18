package id.giyomi.vms.backend.controller.rest.model.spk.request;

public class DetailUkuranJumlahReq {
    private Long ukuranId;
    private Integer jumlah;

    public DetailUkuranJumlahReq() {
    }

    public DetailUkuranJumlahReq(Long ukuranId, Integer jumlah) {
        this.ukuranId = ukuranId;
        this.jumlah = jumlah;
    }

    public Long getUkuranId() {
        return ukuranId;
    }

    public void setUkuranId(Long ukuranId) {
        this.ukuranId = ukuranId;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }
}
