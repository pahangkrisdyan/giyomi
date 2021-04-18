package id.giyomi.vms.backend.controller.restful.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.giyomi.vms.backend.entity.Catatan;
import id.giyomi.vms.backend.entity.Spk;
import id.giyomi.vms.backend.entity.SpkProses;
import id.giyomi.vms.backend.entity.Vendor;
import id.giyomi.vms.backend.repository.SpkProsesRepository;
import id.giyomi.vms.backend.repository.SpkRepository;
import id.giyomi.vms.backend.repository.VendorRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class SpkRestfulModel  extends RestfulModel{

    private Long vendorId;

    public SpkRestfulModel() {
    }

    public SpkRestfulModel(Spk spk) {
        super(spk);
        this.vendorId = spk.getVendor().getId();
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    @JsonIgnore
    public Spk getEntity(VendorRepository vendorRepository){
        Vendor vendor = vendorRepository
                .findById(vendorId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found vendor with id " + vendorId));
        return new Spk(vendor);
    }

    @JsonIgnore
    public Spk getEntityUpdate(Spk source, VendorRepository vendorRepository){
        if(vendorId!=null){
            Vendor vendor = vendorRepository
                    .findById(vendorId)
                    .orElseThrow(()-> new ResourceNotFoundException("Not found vendor with id " + vendorId));
            source.setVendor(vendor);
        }
        return source;
    }
}
