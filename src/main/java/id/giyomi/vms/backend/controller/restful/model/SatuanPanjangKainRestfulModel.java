package id.giyomi.vms.backend.controller.restful.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.giyomi.vms.backend.entity.SatuanPanjangKain;

public class SatuanPanjangKainRestfulModel  extends RestfulModel {

    private String nama;

    public SatuanPanjangKainRestfulModel() {}

    public SatuanPanjangKainRestfulModel(SatuanPanjangKain satuanPanjangKain) {
        super(satuanPanjangKain);
        this.nama = satuanPanjangKain.getNama();
    }


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @JsonIgnore
    public SatuanPanjangKain getEntity() {
        return new SatuanPanjangKain(this.nama);
    }

    @JsonIgnore
    public SatuanPanjangKain getEntityUpdate(SatuanPanjangKain source) {
        if(nama!=null)source.setNama(nama);
        return source;
    }

}
