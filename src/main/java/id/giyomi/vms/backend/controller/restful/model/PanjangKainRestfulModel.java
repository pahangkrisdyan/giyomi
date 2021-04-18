package id.giyomi.vms.backend.controller.restful.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.giyomi.vms.backend.entity.Catatan;
import id.giyomi.vms.backend.entity.PanjangKain;
import id.giyomi.vms.backend.entity.SatuanPanjangKain;
import id.giyomi.vms.backend.entity.SpkProses;
import id.giyomi.vms.backend.repository.PanjangKainRepository;
import id.giyomi.vms.backend.repository.SatuanPanjangKainRepository;
import id.giyomi.vms.backend.repository.SpkProsesRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class PanjangKainRestfulModel  extends RestfulModel{

    private Long satuanPanjangKainId;
    private Double panjang;

    public PanjangKainRestfulModel() {
    }

    public PanjangKainRestfulModel(PanjangKain panjangKain) {
        super(panjangKain);
        this.satuanPanjangKainId = panjangKain.getSatuanPanjangKain().getId();
        this.panjang = panjangKain.getPanjang();
    }

    public Long getSatuanPanjangKainId() {
        return satuanPanjangKainId;
    }

    public void setSatuanPanjangKainId(Long satuanPanjangKainId) {
        this.satuanPanjangKainId = satuanPanjangKainId;
    }

    public Double getPanjang() {
        return panjang;
    }

    public void setPanjang(Double panjang) {
        this.panjang = panjang;
    }

    @JsonIgnore
    public PanjangKain getEntity(SatuanPanjangKainRepository satuanPanjangKainRepository){
        SatuanPanjangKain satuanPanjangKain = satuanPanjangKainRepository
                .findById(satuanPanjangKainId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found satuanPanjangKain with id " + satuanPanjangKainId));
        return new PanjangKain(this.panjang, satuanPanjangKain);
    }

    @JsonIgnore
    public PanjangKain getEntityUpdate(PanjangKain source, SatuanPanjangKainRepository satuanPanjangKainRepository){
        if(panjang!=null)source.setPanjang(panjang);
        if(satuanPanjangKainId!=null){
            SatuanPanjangKain satuanPanjangKain = satuanPanjangKainRepository
                    .findById(satuanPanjangKainId)
                    .orElseThrow(()-> new ResourceNotFoundException("Not found satuanPanjangKain with id " + satuanPanjangKainId));
            source.setSatuanPanjangKain(satuanPanjangKain);
        }
        return source;
    }
}
