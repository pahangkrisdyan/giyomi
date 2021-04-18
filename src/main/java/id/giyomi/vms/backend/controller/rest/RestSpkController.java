package id.giyomi.vms.backend.controller.rest;

import id.giyomi.vms.backend.controller.rest.model.*;
import id.giyomi.vms.backend.controller.rest.model.spk.request.SpkRequestModel;
import id.giyomi.vms.backend.controller.rest.model.spk.response.SpkResponseModel;
import id.giyomi.vms.backend.service.SpkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/rest/spks")
public class RestSpkController {

    private SpkService spkService;

    public RestSpkController(SpkService spkService) {
        this.spkService = spkService;
    }

    @PostMapping(value = "")
    private ResponseEntity<SpkResponseModel> createSpk(@RequestBody SpkRequestModel spkRequestModel, @RequestParam(required = true) SpkStatusEnum status) {
        SpkResponseModel spkResponseModel = spkService.createSpk(spkRequestModel, status);
        return ResponseEntity.ok(spkResponseModel);
    }

    @PostMapping(value = "/{spkId}/nilai")
    private ResponseEntity<PenilaianSpk> submitNilai(@RequestBody PenilaianSpk penilaianSpk, @PathVariable Long spkId) {
        spkService.submitNilai(penilaianSpk, spkId);
        return ResponseEntity.ok(penilaianSpk);
    }

    @GetMapping(value = "/{spkId}")
    private ResponseEntity<SpkResponseModel> getSpk(@PathVariable Long spkId) {
        return ResponseEntity.ok(spkService.getSingleSpk(spkId));
    }

    @GetMapping(value = "")
    private ResponseEntity<List<SpkResponseModel>> getSpks(@RequestParam(required = false) SpkStatusEnum status) {
        return ResponseEntity.ok(spkService.getSpksByStatusName(status));
    }

    @GetMapping(value = "/{spkId}/penilaianVendor")
    private ResponseEntity<AutoFillPenilaianVendor> penilaianVendor(@PathVariable Long spkId){
        return ResponseEntity.ok(spkService.autoFillPenilaianVendor(spkId));
    }

//    @PostMapping(value = "/{spkId}/spkproses/{spkProsesId}/done")
//    private ResponseEntity<SpkProses> spkProsesDone(@PathVariable Long spkId, @PathVariable Long spkProsesId) {
//        return ResponseEntity.ok(spkService.spkProsesDone(spkId, spkProsesId));
//    }
}
