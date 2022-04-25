package pl.komoor.pcbuilder.repository.tools;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.komoor.pcbuilder.models.tools.FileToDatabase;

import java.util.stream.Stream;

public interface FileToDatabaseRepository extends JpaRepository<FileToDatabase, String> {

    public Page<FileToDatabase> findAll(Pageable pageRequest);

}
