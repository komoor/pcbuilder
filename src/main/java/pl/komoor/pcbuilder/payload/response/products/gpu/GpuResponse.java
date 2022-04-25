package pl.komoor.pcbuilder.payload.response.products.gpu;

import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.products.GpuDTO;

public class GpuResponse extends SuccessResponse {

    private GpuDTO gpu;

    public GpuResponse(GpuDTO gpu) {
        this.gpu = gpu;
        addFullMessage("Pobrano GPU.");
    }

    public GpuDTO getGpu() {
        return gpu;
    }

    public void setGpu(GpuDTO gpu) {
        this.gpu = gpu;
    }
}
