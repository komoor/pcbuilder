package pl.komoor.pcbuilder.payload.response.products.cpuCooler;

import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.products.CpuCoolerDTO;

public class CpuCoolerResponse extends SuccessResponse {

    private CpuCoolerDTO cpuCooler;

    public CpuCoolerResponse(CpuCoolerDTO cpuCooler) {
        this.cpuCooler = cpuCooler;
        addFullMessage("Pobrano CPU Cooler");
    }

    public CpuCoolerDTO getCpuCooler() {
        return cpuCooler;
    }

    public void setCpuCooler(CpuCoolerDTO cpuCooler) {
        this.cpuCooler = cpuCooler;
    }
}
