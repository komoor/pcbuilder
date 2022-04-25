package pl.komoor.pcbuilder.models.productDetails.gpu;

import javax.persistence.*;

@Entity
@Table(name = "gpu_memory_type")
public class GpuMemoryType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String gpuMemoryTypeName;

    public GpuMemoryType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGpuMemoryTypeName() {
        return gpuMemoryTypeName;
    }

    public void setGpuMemoryTypeName(String gpuMemoryTypeName) {
        this.gpuMemoryTypeName = gpuMemoryTypeName;
    }
}
