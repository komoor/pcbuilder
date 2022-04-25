package pl.komoor.pcbuilder.models.tools;

import org.hibernate.annotations.GenericGenerator;
import pl.komoor.pcbuilder.services.dto.tools.FileToDatabaseDTO;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Optional;

@Entity
@Table(	name = "sbin")
public class FileToDatabase {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String type;

    @Lob
    private byte[] data;

    public FileToDatabase() {

    }

    public FileToDatabase(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "FileToDatabase{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }

}
