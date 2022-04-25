package pl.komoor.pcbuilder.services.dto.productsDetails.cpu;

import pl.komoor.pcbuilder.models.productDetails.cpu.CpuSocket;

public class CpuSocketDTO {

    private Long id;
    private String name;

    public CpuSocketDTO() {
    }

    public CpuSocketDTO(Long id, String socketName) {
        this.id = id;
        this.name = socketName;
    }

    public static CpuSocketDTO build(CpuSocket cpuSocket) {

        return new CpuSocketDTO(
                cpuSocket.getId(),
                cpuSocket.getSocketName()
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
