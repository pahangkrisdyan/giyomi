package id.giyomi.vms.backend.controller.restful.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.giyomi.vms.backend.entity.Catatan;
import id.giyomi.vms.backend.entity.NilaiSpk;
import id.giyomi.vms.backend.entity.Spk;
import id.giyomi.vms.backend.entity.SpkProses;
import id.giyomi.vms.backend.repository.NilaiSpkRepository;
import id.giyomi.vms.backend.repository.SpkProsesRepository;
import id.giyomi.vms.backend.repository.SpkRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class NilaiSpkRestfulModel extends RestfulModel{

    private Long spkId;
    private Double kerapihan;
    private Double kualitas;
    private Double waktu;
    private Double jumlah;
    private String komentar;

    public NilaiSpkRestfulModel() {
    }

    public NilaiSpkRestfulModel(NilaiSpk nilaiSpk) {
        super(nilaiSpk);
        this.jumlah = nilaiSpk.getJumlah();
        this.kerapihan = nilaiSpk.getKerapihan();
        this.kualitas = nilaiSpk.getKualitas();
        this.waktu = nilaiSpk.getWaktu();
        this.spkId = nilaiSpk.getSpk().getId();
        this.komentar = nilaiSpk.getKomentar();
    }

    public Long getSpkId() {
        return spkId;
    }

    public void setSpkId(Long spkId) {
        this.spkId = spkId;
    }

    public double getKerapihan() {
        return kerapihan;
    }

    public void setKerapihan(double kerapihan) {
        this.kerapihan = kerapihan;
    }

    public double getKualitas() {
        return kualitas;
    }

    public void setKualitas(double kualitas) {
        this.kualitas = kualitas;
    }

    public double getWaktu() {
        return waktu;
    }

    public void setWaktu(double waktu) {
        this.waktu = waktu;
    }

    public double getJumlah() {
        return jumlah;
    }

    public void setJumlah(double jumlah) {
        this.jumlah = jumlah;
    }

    public void setKerapihan(Double kerapihan) {
        this.kerapihan = kerapihan;
    }

    public void setKualitas(Double kualitas) {
        this.kualitas = kualitas;
    }

    public void setWaktu(Double waktu) {
        this.waktu = waktu;
    }

    public void setJumlah(Double jumlah) {
        this.jumlah = jumlah;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    @JsonIgnore
    public NilaiSpk getEntity(SpkRepository spkRepository, NilaiSpkRepository nilaiSpkRepository){
        Spk spk = spkRepository
                .findById(spkId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found spk with id " + spkId));
        NilaiSpk nilaiSpk = nilaiSpkRepository.save(new NilaiSpk(spk, this.kerapihan, this.kualitas, this.waktu, this.jumlah, this.komentar));
        spk.setNilaiSpk(nilaiSpk);
        return nilaiSpk;
    }

    @JsonIgnore
    public NilaiSpk getEntityUpdate(NilaiSpk source, SpkRepository spkRepository){
        if(waktu!=null)source.setWaktu(this.waktu);
        if(jumlah!=null)source.setJumlah(this.jumlah);
        if(kerapihan!=null)source.setKerapihan(this.kerapihan);
        if(kualitas!=null)source.setKualitas(this.kualitas);
        if(komentar!=null)source.setKomentar(this.komentar);
        if(spkId!=null){
            Spk spk = spkRepository
                    .findById(spkId)
                    .orElseThrow(()-> new ResourceNotFoundException("Not found spk with id " + spkId));
            source.setSpk(spk);
        }
        return source;
    }
}
