package id.giyomi.vms.backend.controller.restful;

import id.giyomi.vms.backend.entity.Vendor;
import io.swagger.models.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface RestfulController<RestfulModel, Entity> {

    @GetMapping(value = "/{id}")
    ResponseEntity<RestfulModel> getSingle(@PathVariable Long id);

    @GetMapping(value = "")
    ResponseEntity<List<RestfulModel>> getList();

    @PostMapping(value = "")
    ResponseEntity<RestfulModel> create(@RequestBody RestfulModel model);

    @PutMapping(value = "/{id}")
    ResponseEntity<RestfulModel> update(@PathVariable Long id, @RequestBody RestfulModel model) throws Exception;

    @DeleteMapping(value = "/{id}")
    ResponseEntity<String> delete(@PathVariable Long id);

    Entity findById(Long id);

}
