package pl.komoor.pcbuilder.services.dto.tools;

import org.springframework.data.domain.Page;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.komoor.pcbuilder.models.tools.FileToDatabase;

public class FileToDatabaseDTO {

    private String id;
    private String name;
    private String url;
    private String type;
    private long size;

    public FileToDatabaseDTO(String id, String name, String url, String type, long size) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
    }

    public FileToDatabaseDTO() {

    }

    public static FileToDatabaseDTO build(FileToDatabase file) {

        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/images/")
                .path(file.getId())
                .toUriString();

        return new FileToDatabaseDTO(
                file.getId(),
                file.getName(),
                fileDownloadUri,
                file.getType(),
                file.getData().length);

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
