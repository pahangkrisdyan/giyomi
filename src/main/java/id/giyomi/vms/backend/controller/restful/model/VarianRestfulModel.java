package id.giyomi.vms.backend.controller.restful.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.giyomi.vms.backend.entity.Koleksi;
import id.giyomi.vms.backend.entity.Varian;
import id.giyomi.vms.backend.repository.KoleksiRepository;
import id.giyomi.vms.backend.repository.VarianRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class VarianRestfulModel  extends RestfulModel{

    private String varian;
    private Long koleksiId;

    public VarianRestfulModel() {
    }

    public VarianRestfulModel(Varian varian) {
        super(varian);
        this.varian = varian.getVarian();
        this.koleksiId = varian.getKoleksi().getId();
    }

    public String getVarian() {
        return varian;
    }

    public void setVarian(String varian) {
        this.varian = varian;
    }

    public Long getKoleksiId() {
        return koleksiId;
    }

    public void setKoleksiId(Long koleksiId) {
        this.koleksiId = koleksiId;
    }

    @JsonIgnore
    public Varian getEntity(KoleksiRepository koleksiRepository) {
        Koleksi koleksi = koleksiRepository
                .findById(koleksiId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found koleksi with id " + koleksiId));
        return new Varian(koleksi, varian);
    }

    @JsonIgnore
    public Varian getEntityUpdate(Varian source, KoleksiRepository koleksiRepository) {
        if(koleksiId!=null){
            Koleksi koleksi = koleksiRepository
                    .findById(koleksiId)
                    .orElseThrow(()-> new ResourceNotFoundException("Not found koleksi with id " + koleksiId));
            source.setKoleksi(koleksi);
        }
        if(varian!=null) source.setVarian(varian);
        return source;
    }
}
