package id.giyomi.vms.backend.controller.restful.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.giyomi.vms.backend.entity.JenisKoleksi;
import id.giyomi.vms.backend.entity.Koleksi;
import id.giyomi.vms.backend.entity.Varian;
import id.giyomi.vms.backend.repository.JenisKoleksiRepository;
import id.giyomi.vms.backend.repository.KoleksiRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import javax.persistence.ManyToOne;

public class KoleksiRestfulModel  extends RestfulModel {

    private String nama;
    private String fotoDownloadUrl;
    private Long jenisKoleksiId;
    private String detailAksesoris;

    public KoleksiRestfulModel() {}

    public KoleksiRestfulModel(Koleksi koleksi) {
        super(koleksi);
        this.nama = koleksi.getNama();
        this.fotoDownloadUrl = koleksi.getFotoDownloadUrl();
        this.jenisKoleksiId = koleksi.getJenisKoleksi().getId();
        this.detailAksesoris = koleksi.getDetailAksesoris();
    }

    public String getDetailAksesoris() {
        return detailAksesoris;
    }

    public void setDetailAksesoris(String detailAksesoris) {
        this.detailAksesoris = detailAksesoris;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getFotoDownloadUrl() {
        return fotoDownloadUrl;
    }

    public void setFotoDownloadUrl(String fotoDownloadUrl) {
        this.fotoDownloadUrl = fotoDownloadUrl;
    }

    public Long getJenisKoleksiId() {
        return jenisKoleksiId;
    }

    public void setJenisKoleksiId(Long jenisKoleksiId) {
        this.jenisKoleksiId = jenisKoleksiId;
    }

    @JsonIgnore
    public Koleksi getEntity(JenisKoleksiRepository jenisKoleksiRepository) {
        JenisKoleksi jenisKoleksi = jenisKoleksiRepository
                .findById(jenisKoleksiId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found koleksijenisid with id " + jenisKoleksiId));
        return new Koleksi(nama, fotoDownloadUrl, jenisKoleksi, detailAksesoris);
    }

    @JsonIgnore
    public Koleksi getEntityUpdate(Koleksi source, JenisKoleksiRepository jenisKoleksiRepository) {
        if(jenisKoleksiId!=null){
            JenisKoleksi jenisKoleksi = jenisKoleksiRepository
                    .findById(jenisKoleksiId)
                    .orElseThrow(()-> new ResourceNotFoundException("Not found koleksijenisid with id " + jenisKoleksiId));
            source.setJenisKoleksi(jenisKoleksi);
        }
        if(nama!=null) source.setNama(nama);
        if(detailAksesoris!=null) source.setDetailAksesoris(detailAksesoris);
        if(fotoDownloadUrl!=null) source.setFotoDownloadUrl(fotoDownloadUrl);
        return source;
    }

}
