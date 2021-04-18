package id.giyomi.vms.backend.controller.rest.model.cdn;

public class CdnUploadResponse {
    private String downloadUrl;

    public CdnUploadResponse() {
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public CdnUploadResponse(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
