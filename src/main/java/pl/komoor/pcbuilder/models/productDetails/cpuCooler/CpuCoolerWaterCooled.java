package pl.komoor.pcbuilder.models.productDetails.cpuCooler;

import javax.persistence.*;


@Entity
@Table(name = "cpu_cooler_water_cooled")
public class CpuCoolerWaterCooled {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String cpuCoolerWaterCooledName;

    public CpuCoolerWaterCooled() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpuCoolerWaterCooledName() {
        return cpuCoolerWaterCooledName;
    }

    public void setCpuCoolerWaterCooledName(String cpuCoolerWaterCooledName) {
        this.cpuCoolerWaterCooledName = cpuCoolerWaterCooledName;
    }
}
