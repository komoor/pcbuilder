package pl.komoor.pcbuilder.models.productDetails.gpu;

import javax.persistence.*;

@Entity
@Table(name = "gpu_chipset")
public class GpuChipset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String gpuChipsetName;

    public GpuChipset() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGpuChipsetName() {
        return gpuChipsetName;
    }

    public void setGpuChipsetName(String gpuChipsetName) {
        this.gpuChipsetName = gpuChipsetName;
    }
}
