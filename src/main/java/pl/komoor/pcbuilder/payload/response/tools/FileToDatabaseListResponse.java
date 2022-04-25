package pl.komoor.pcbuilder.payload.response.tools;

import pl.komoor.pcbuilder.payload.response.basic.PageMeta;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.tools.FileToDatabaseDTO;

import java.util.List;

public class FileToDatabaseListResponse extends SuccessResponse {

    PageMeta pageMeta;
    List<FileToDatabaseDTO> files;


    public FileToDatabaseListResponse(List<FileToDatabaseDTO> files, PageMeta pageMeta) {
        this.pageMeta = pageMeta;
        this.files = files;
        addFullMessage("Pomyślnie pobrano obrazy");

    }

    public List<FileToDatabaseDTO> getFiles() {
        return files;
    }

    public void setFiles(List<FileToDatabaseDTO> filess) {
        this.files = filess;
    }

    public PageMeta getPageMeta() {
        return pageMeta;
    }

    public void setPageMeta(PageMeta pageMeta) {
        this.pageMeta = pageMeta;
    }
}
