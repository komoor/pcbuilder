package pl.komoor.pcbuilder.payload.response.products.cpu;

import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.products.CpuDTO;


public class CpuResponse extends SuccessResponse {

    private CpuDTO cpu;

    public CpuResponse(CpuDTO cpu) {
        this.cpu = cpu;
        addFullMessage("Pomyślnie pobrano CPU");
    }

    public CpuDTO getCpu() {
        return cpu;
    }

    public void setCpu(CpuDTO cpu) {
        this.cpu = cpu;
    }
}
