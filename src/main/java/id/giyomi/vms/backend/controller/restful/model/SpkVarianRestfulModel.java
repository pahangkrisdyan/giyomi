package id.giyomi.vms.backend.controller.restful.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.giyomi.vms.backend.entity.PanjangKain;
import id.giyomi.vms.backend.entity.Spk;
import id.giyomi.vms.backend.entity.SpkVarian;
import id.giyomi.vms.backend.entity.Varian;
import id.giyomi.vms.backend.repository.PanjangKainRepository;
import id.giyomi.vms.backend.repository.SpkRepository;
import id.giyomi.vms.backend.repository.VarianRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class SpkVarianRestfulModel extends RestfulModel{

    private Long spkId;
    private Long varianId;
    private Long panjangKainId;

    public SpkVarianRestfulModel() {
    }

    public SpkVarianRestfulModel(SpkVarian spkVarian) {
        super(spkVarian);
        this.spkId = spkVarian.getSpk().getId();
        this.varianId = spkVarian.getVarian().getId();
        this.panjangKainId = spkVarian.getPanjangKain().getId();
    }

    public Long getSpkId() {
        return spkId;
    }

    public void setSpkId(Long spkId) {
        this.spkId = spkId;
    }

    public Long getVarianId() {
        return varianId;
    }

    public void setVarianId(Long varianId) {
        this.varianId = varianId;
    }

    public Long getPanjangKainId() {
        return panjangKainId;
    }

    public void setPanjangKainId(Long panjangKainId) {
        this.panjangKainId = panjangKainId;
    }

    @JsonIgnore
    public SpkVarian getEntity(SpkRepository spkRepository, VarianRepository varianRepository, PanjangKainRepository panjangKainRepository){
        Spk spk = spkRepository
                .findById(spkId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found spk with id " + spkId));
        Varian varian = varianRepository
                .findById(varianId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found varian with id " + varianId));
        PanjangKain panjangKain = panjangKainRepository
                .findById(panjangKainId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found panjangKain with id " + panjangKainId));
        return new SpkVarian(spk, varian, panjangKain);
    }

    @JsonIgnore
    public SpkVarian getEntityUpdate(SpkVarian source, SpkRepository spkRepository, VarianRepository varianRepository, PanjangKainRepository panjangKainRepository){
        if(spkId!=null){
            Spk spk = spkRepository
                    .findById(spkId)
                    .orElseThrow(()-> new ResourceNotFoundException("Not found spk with id " + spkId));
            source.setSpk(spk);
        }
        if(varianId!=null){
            Varian varian = varianRepository
                    .findById(varianId)
                    .orElseThrow(()-> new ResourceNotFoundException("Not found varian with id " + varianId));
            source.setVarian(varian);
        }
        if(panjangKainId!=null){
            PanjangKain panjangKain = panjangKainRepository
                    .findById(panjangKainId)
                    .orElseThrow(()-> new ResourceNotFoundException("Not found panjangKain with id " + panjangKainId));
            source.setPanjangKain(panjangKain);
        }
        return source;
    }
}
