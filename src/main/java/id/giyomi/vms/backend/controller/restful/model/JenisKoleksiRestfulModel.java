package id.giyomi.vms.backend.controller.restful.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.giyomi.vms.backend.entity.JenisKoleksi;
import id.giyomi.vms.backend.entity.Proses;

public class JenisKoleksiRestfulModel  extends RestfulModel {

    private Long id;
    private String nama;

    public JenisKoleksiRestfulModel() {
    }

    public JenisKoleksiRestfulModel(JenisKoleksi jenisKoleksi) {
        super(jenisKoleksi);
        this.nama = jenisKoleksi.getNama();
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @JsonIgnore
    public JenisKoleksi getEntity(){
        return new JenisKoleksi(this.nama);
    }

    @JsonIgnore
    public JenisKoleksi getEntityUpdate(JenisKoleksi source){
        if(nama!=null)source.setNama(nama);
        return source;
    }
}
