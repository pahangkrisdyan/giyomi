package id.giyomi.vms.backend.controller.rest.model;

public class AutoFillPenilaianVendor {
    private Double waktu;
    private Double jumlah;

    public AutoFillPenilaianVendor() {
    }

    public AutoFillPenilaianVendor(Double waktu, Double jumlah) {
        this.waktu = waktu;
        this.jumlah = jumlah;
    }

    public Double getWaktu() {
        return waktu;
    }

    public void setWaktu(Double waktu) {
        this.waktu = waktu;
    }

    public Double getJumlah() {
        return jumlah;
    }

    public void setJumlah(Double jumlah) {
        this.jumlah = jumlah;
    }
}
