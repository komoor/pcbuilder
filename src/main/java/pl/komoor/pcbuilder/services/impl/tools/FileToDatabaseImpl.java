package pl.komoor.pcbuilder.services.impl.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.komoor.pcbuilder.models.tools.FileToDatabase;
import pl.komoor.pcbuilder.repository.tools.FileToDatabaseRepository;
import pl.komoor.pcbuilder.services.dto.tools.FileToDatabaseDTO;
import pl.komoor.pcbuilder.services.products.product.ProductService;
import pl.komoor.pcbuilder.services.tools.FileToDatabaseService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FileToDatabaseImpl implements FileToDatabaseService {

    @Autowired
    FileToDatabaseRepository fileToDatabaseRepository;

    @Autowired
    ProductService productService;


    @Override
    public Optional<FileToDatabase> storeFile(MultipartFile file) throws IOException {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        FileToDatabase fileDB = new FileToDatabase(fileName, file.getContentType(), file.getBytes());

        fileToDatabaseRepository.save(fileDB);

        Optional<FileToDatabase> findFile = findById(fileDB.getId());

        return findFile;
    }

    @Override
    public Optional<FileToDatabase> findById(String id) {

            return fileToDatabaseRepository.findById(id);

    }

    @Override
    public Page<FileToDatabase> getAllFiles(Pageable pageable) {

        Page<FileToDatabase> files = fileToDatabaseRepository.findAll(pageable);

        return files;
    }

    @Override
    public List<FileToDatabaseDTO> convertToDto(Page<FileToDatabase> files) {

        List<FileToDatabaseDTO> fileToDatabaseDTOList = files.getContent().stream().map(FileToDatabaseDTO::build).collect(Collectors.toList());
        return fileToDatabaseDTOList;


    }

    @Override
    public void deleteFile(FileToDatabase file) {



            fileToDatabaseRepository.delete(file);


    }


}
