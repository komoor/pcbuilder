package pl.komoor.pcbuilder.services.dto.productsDetails.gpu;

import pl.komoor.pcbuilder.models.productDetails.gpu.GpuMemoryType;

public class GpuMemoryTypeDTO {

    private Long id;
    private String name;

    public GpuMemoryTypeDTO() {
    }

    public GpuMemoryTypeDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static GpuMemoryTypeDTO build(GpuMemoryType gpuMemoryType) {

        return new GpuMemoryTypeDTO(
                gpuMemoryType.getId(),
                gpuMemoryType.getGpuMemoryTypeName()
        );

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
