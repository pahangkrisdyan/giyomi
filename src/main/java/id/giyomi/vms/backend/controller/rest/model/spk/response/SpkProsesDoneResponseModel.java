package id.giyomi.vms.backend.controller.rest.model.spk.response;

import id.giyomi.vms.backend.entity.SpkProses;

import java.util.Date;

public class SpkProsesDoneResponseModel {
    private Long spkProsesId;
    private Long prosesId;
    private String namaProses;
    private Integer durasiRencana;
    private Integer durasiNyata;
    private Date tanggalSelesai;

    public SpkProsesDoneResponseModel() {
    }

    public SpkProsesDoneResponseModel(SpkProses spkProses) {
        this.spkProsesId = spkProses.getId();
        this.prosesId = spkProses.getProses().getId();
        this.namaProses = spkProses.getProses().getNama();
        this.durasiRencana = spkProses.getDurasiRencana();
        this.durasiNyata = spkProses.getDurasiNyata();
        this.tanggalSelesai = spkProses.getTanggalSelesai();
    }

    public SpkProsesDoneResponseModel(Long spkProsesId, Long prosesId, String namaProses, Integer durasiRencana, Integer durasiNyata, Date tanggalSelesai) {
        this.spkProsesId = spkProsesId;
        this.prosesId = prosesId;
        this.namaProses = namaProses;
        this.durasiRencana = durasiRencana;
        this.durasiNyata = durasiNyata;
        this.tanggalSelesai = tanggalSelesai;
    }

    public Long getSpkProsesId() {
        return spkProsesId;
    }

    public void setSpkProsesId(Long spkProsesId) {
        this.spkProsesId = spkProsesId;
    }

    public Long getProsesId() {
        return prosesId;
    }

    public void setProsesId(Long prosesId) {
        this.prosesId = prosesId;
    }

    public String getNamaProses() {
        return namaProses;
    }

    public void setNamaProses(String namaProses) {
        this.namaProses = namaProses;
    }

    public Integer getDurasiRencana() {
        return durasiRencana;
    }

    public void setDurasiRencana(Integer durasiRencana) {
        this.durasiRencana = durasiRencana;
    }

    public Integer getDurasiNyata() {
        return durasiNyata;
    }

    public void setDurasiNyata(Integer durasiNyata) {
        this.durasiNyata = durasiNyata;
    }

    public Date getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(Date tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }
}
