package id.giyomi.vms.backend.controller.rest.model.spk.response;

import java.util.ArrayList;
import java.util.Date;

public class DetailSpkProses {
    private ArrayList<DetailCatatan> catatans;
    private Date estSelesai;
    private String proses;
    private Long spkProsesId;
    private Integer durasiNyata;
    private Integer durasiRencana;
    private Date tanggalSelesai;

    public DetailSpkProses() {
    }

    public DetailSpkProses(ArrayList<DetailCatatan> catatans, Date estSelesai, String proses, Long id, Date tanggalSelesai, Integer durasiNyata, Integer durasiRencana) {
        this.catatans = catatans;
        this.estSelesai = estSelesai;
        this.proses = proses;
        this.spkProsesId = id;
        this.tanggalSelesai = tanggalSelesai;
        this.durasiNyata = durasiNyata;
        this.durasiRencana = durasiRencana;
    }
    

    public Integer getDurasiRencana() {
        return durasiRencana;
    }

    public void setDurasiRencana(Integer durasiRencana) {
        this.durasiRencana = durasiRencana;
    }

    public ArrayList<DetailCatatan> getCatatans() {
        return catatans;
    }

    public void setCatatans(ArrayList<DetailCatatan> catatans) {
        this.catatans = catatans;
    }

    public Date getEstSelesai() {
        return estSelesai;
    }

    public void setEstSelesai(Date estSelesai) {
        this.estSelesai = estSelesai;
    }

    public String getProses() {
        return proses;
    }

    public void setProses(String proses) {
        this.proses = proses;
    }

    public Long getSpkProsesId() {
        return spkProsesId;
    }

    public void setSpkProsesId(Long id) {
        this.spkProsesId = id;
    }

    public Date getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(Date tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public Integer getDurasiNyata() {
        return durasiNyata;
    }

    public void setDurasiNyata(Integer durasiNyata) {
        this.durasiNyata = durasiNyata;
    }
}
