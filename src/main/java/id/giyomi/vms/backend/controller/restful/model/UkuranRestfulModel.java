package id.giyomi.vms.backend.controller.restful.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.giyomi.vms.backend.entity.Ukuran;

public class UkuranRestfulModel  extends RestfulModel{

    private String nama;

    public UkuranRestfulModel() {
    }

    public UkuranRestfulModel(Ukuran ukuran) {
        super(ukuran);
        this.nama = ukuran.getNama();
    }


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @JsonIgnore
    public Ukuran getEntity() {
        return new Ukuran(this.nama);
    }

    @JsonIgnore
    public Ukuran getEntityUpdate(Ukuran source) {
        if(nama!=null)source.setNama(nama);
        return source;
    }
}
