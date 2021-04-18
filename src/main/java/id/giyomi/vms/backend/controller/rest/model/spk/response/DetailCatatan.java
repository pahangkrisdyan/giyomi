package id.giyomi.vms.backend.controller.rest.model.spk.response;

import java.util.Date;

public class DetailCatatan {
    private String pencatat;
    private String isi;
    private Date tanggal;

    public DetailCatatan() {
    }

    public DetailCatatan(String pencatat, String isi, Date tanggal) {
        this.pencatat = pencatat;
        this.isi = isi;
        this.tanggal = tanggal;
    }

    public String getPencatat() {
        return pencatat;
    }

    public void setPencatat(String pencatat) {
        this.pencatat = pencatat;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
}
