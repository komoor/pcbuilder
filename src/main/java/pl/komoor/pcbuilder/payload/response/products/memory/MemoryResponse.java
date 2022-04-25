package pl.komoor.pcbuilder.payload.response.products.memory;

import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.products.CpuDTO;
import pl.komoor.pcbuilder.services.dto.products.MemoryDTO;

public class MemoryResponse extends SuccessResponse {

    private MemoryDTO memory;

    public MemoryResponse(MemoryDTO memory) {
        this.memory = memory;
        addFullMessage("Pobrano pamięć.");
    }

    public MemoryDTO getMemory() {
        return memory;
    }

    public void setMemory(MemoryDTO memory) {
        this.memory = memory;
    }
}
