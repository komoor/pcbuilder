package pl.komoor.pcbuilder.services.dto.builds;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.komoor.pcbuilder.models.builds.PcPartsList;
import pl.komoor.pcbuilder.services.dto.products.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PcPartsListDTO {

    private Long id;
    private Date createDate;
    private String listName;
    private String username;
    private String userAvatarUrl;
    private PcPartsDTO pcParts;

    public PcPartsListDTO() {
    }

    public PcPartsListDTO(Long id, Date createDate, String listName, String username, String userAvatarUrl, PcPartsDTO pcParts) {
        this.id = id;
        this.createDate = createDate;
        this.listName = listName;
        this.username = username;
        this.userAvatarUrl = userAvatarUrl;
        this.pcParts = pcParts;
    }

    public static PcPartsListDTO build(PcPartsList pcPartsList) {

        String imageUrl = null;

        if(pcPartsList.getUserId().getFileToDatabase() != null)
            imageUrl = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/images/")
                    .path(pcPartsList.getUserId().getFileToDatabase().getId())
                    .toUriString();

        PcPartsDTO pcPartsDTO = Optional.ofNullable(pcPartsList).map(PcPartsDTO::build).get();


        List<MemoryDTO> memoryDTOList = pcPartsList.getMemory().stream().map(MemoryDTO::build).collect(Collectors.toList());
        List<StorageDTO> storageDTOList = pcPartsList.getStorage().stream().map(StorageDTO::build).collect(Collectors.toList());

        GpuDTO gpuDTO = Optional.ofNullable(pcPartsList.getGpuId()).map(GpuDTO::build).get();
        CpuDTO cpuDTO = Optional.ofNullable(pcPartsList.getCpuId()).map(CpuDTO::build).get();
        CpuCoolerDTO cpuCoolerDTO = Optional.ofNullable(pcPartsList.getCpuCoolerId()).map(CpuCoolerDTO::build).get();
        MotherboardDTO motherboardDTO = Optional.ofNullable(pcPartsList.getMotherboardId()).map(MotherboardDTO::build).get();
        PsuDTO psuDTO = Optional.ofNullable(pcPartsList.getPsuId()).map(PsuDTO::build).get();
        CaseDTO caseDTO = Optional.ofNullable(pcPartsList.getCaseId()).map(CaseDTO::build).get();


        return new PcPartsListDTO(
                pcPartsList.getId(),
                pcPartsList.getCreateDate(),
                pcPartsList.getListName(),
                pcPartsList.getUserId().getUsername(),
                imageUrl,
                pcPartsDTO
                );

    }

    public String getUserAvatarUrl() {
        return userAvatarUrl;
    }

    public void setUserAvatarUrl(String userAvatarUrl) {
        this.userAvatarUrl = userAvatarUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PcPartsDTO getPcParts() {
        return pcParts;
    }

    public void setPcParts(PcPartsDTO pcParts) {
        this.pcParts = pcParts;
    }
}

