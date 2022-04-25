package pl.komoor.pcbuilder.payload.response.products.cpuCooler;

import pl.komoor.pcbuilder.payload.response.basic.PageMeta;
import pl.komoor.pcbuilder.payload.response.basic.SuccessResponse;
import pl.komoor.pcbuilder.services.dto.products.CpuCoolerDTO;

import java.util.List;

public class CpuCoolerListResponse extends SuccessResponse {

    private PageMeta pageMeta;
    private List<CpuCoolerDTO> cpuCoolers;

    public CpuCoolerListResponse(List<CpuCoolerDTO> cpuCoolers, PageMeta pageMeta) {
        this.pageMeta = pageMeta;
        this.cpuCoolers = cpuCoolers;
        addFullMessage("Pobrano listę CPU Coolers.");
    }

    public PageMeta getPageMeta() {
        return pageMeta;
    }

    public void setPageMeta(PageMeta pageMeta) {
        this.pageMeta = pageMeta;
    }

    public List<CpuCoolerDTO> getCpuCoolers() {
        return cpuCoolers;
    }

    public void setCpuCoolers(List<CpuCoolerDTO> cpuCoolers) {
        this.cpuCoolers = cpuCoolers;
    }
}
