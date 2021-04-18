package id.giyomi.vms.backend.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "cdn";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
