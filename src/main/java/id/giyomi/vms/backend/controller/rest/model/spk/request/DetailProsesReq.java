package id.giyomi.vms.backend.controller.rest.model.spk.request;

public class DetailProsesReq {
    private Long prosesId;
    private Integer durasiRencana;

    public DetailProsesReq(Long prosesId, Integer durasiRencana) {
        this.prosesId = prosesId;
        this.durasiRencana = durasiRencana;
    }

    public DetailProsesReq() {
    }

    public Long getProsesId() {
        return prosesId;
    }

    public void setProsesId(Long prosesId) {
        this.prosesId = prosesId;
    }

    public Integer getDurasiRencana() {
        return durasiRencana;
    }

    public void setDurasiRencana(Integer durasiRencana) {
        this.durasiRencana = durasiRencana;
    }
}
