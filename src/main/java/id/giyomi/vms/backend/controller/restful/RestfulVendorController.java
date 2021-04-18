package id.giyomi.vms.backend.controller.restful;

import id.giyomi.vms.backend.controller.restful.model.VendorRestfulModel;
import id.giyomi.vms.backend.entity.Vendor;
import id.giyomi.vms.backend.repository.VendorRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/restful/vendors")
public class RestfulVendorController implements RestfulController<VendorRestfulModel, Vendor> {

    private VendorRepository vendorRepository;

    public RestfulVendorController(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public Vendor findById(Long id){
        Vendor vendor = vendorRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found vendor with id " + id));
        return vendor;
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<VendorRestfulModel> getSingle(Long id) {
        Vendor vendor = findById(id);
        VendorRestfulModel vendorRestful = new VendorRestfulModel(vendor);
        return ResponseEntity.ok(vendorRestful);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<VendorRestfulModel>> getList() {
        ArrayList<VendorRestfulModel> vendorRestfulModels = new ArrayList<>();
        vendorRepository.findAll().forEach(vendor -> {
            vendorRestfulModels.add(new VendorRestfulModel(vendor));
        });
        return ResponseEntity.ok(vendorRestfulModels);
    }

    @Override
    @PostMapping
    public ResponseEntity<VendorRestfulModel> create(@RequestBody VendorRestfulModel vendorRestfulModel) {
        return ResponseEntity.ok(new VendorRestfulModel(vendorRepository.save(vendorRestfulModel.getEntity())));
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<VendorRestfulModel> update(@PathVariable Long id, @RequestBody VendorRestfulModel vendorRestfulModel) {
        return ResponseEntity.ok(new VendorRestfulModel(vendorRepository.save(vendorRestfulModel.getEntityUpdate(findById(id)))));
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(Long id) {
        findById(id);
        vendorRepository.deleteById(id);
        return ResponseEntity.ok("Vendor with id " + id + " deleted successfully");
    }
}
