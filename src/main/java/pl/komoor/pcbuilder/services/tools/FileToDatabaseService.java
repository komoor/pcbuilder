package pl.komoor.pcbuilder.services.tools;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import pl.komoor.pcbuilder.models.tools.FileToDatabase;
import pl.komoor.pcbuilder.services.dto.tools.FileToDatabaseDTO;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FileToDatabaseService {

    public Optional<FileToDatabase> storeFile(MultipartFile file) throws IOException;

    Optional<FileToDatabase> findById(String id);

    public Page<FileToDatabase> getAllFiles(Pageable pageable);

    public List<FileToDatabaseDTO> convertToDto(Page<FileToDatabase> page);

    public void deleteFile(FileToDatabase file);


}
