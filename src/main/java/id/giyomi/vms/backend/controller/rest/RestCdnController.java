package id.giyomi.vms.backend.controller.rest;

import id.giyomi.vms.backend.controller.rest.model.cdn.CdnUploadResponse;
import id.giyomi.vms.backend.service.FileSystemStorageService;
import lombok.var;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping(value = "/rest/cdn")
public class RestCdnController {

    @Value("${base.url}")
    private String baseUrl;

    private FileSystemStorageService fileSystemStorageService;

    public RestCdnController(FileSystemStorageService fileSystemStorageService) {
        this.fileSystemStorageService = fileSystemStorageService;
    }

    @PostMapping()
    public ResponseEntity<CdnUploadResponse> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String fileName = fileSystemStorageService.store(file);
        return ResponseEntity.ok(new CdnUploadResponse("http://" + baseUrl + "/rest/cdn/" + fileName));
    }

    @GetMapping(
            value = "/{fileName}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody
    ResponseEntity<byte[]> getFile(@PathVariable String fileName) throws IOException {
        String path = "cdn/" + fileName;
        try {
            FileInputStream fis = new FileInputStream(path);
            return ResponseEntity.ok(IOUtils.toByteArray(fis));
        }catch (FileNotFoundException nfe){
            return ResponseEntity.notFound().build();
        }
    }
}
