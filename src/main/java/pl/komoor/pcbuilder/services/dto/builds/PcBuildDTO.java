package pl.komoor.pcbuilder.services.dto.builds;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.komoor.pcbuilder.models.builds.PcBuild;
import pl.komoor.pcbuilder.services.dto.users.UserDetailsDTO;

import java.util.Date;
import java.util.Optional;

public class PcBuildDTO {


    private Long id;
    private String buildName;
    private String buildDescription;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date buildDate;
    private Date createDate;
    private UserDetailsDTO user;
    private String imageUrl;
    private PcBuildPartsListDTO pcBuildPartsList;

    public PcBuildDTO(Long id, String buildName, String buildDescription, Date buildDate, Date createDate, UserDetailsDTO user, String imageUrl, PcBuildPartsListDTO pcBuildPartsList) {
        this.id = id;
        this.buildName = buildName;
        this.buildDescription = buildDescription;
        this.buildDate = buildDate;
        this.createDate = createDate;
        this.user = user;
        this.imageUrl = imageUrl;
        this.pcBuildPartsList = pcBuildPartsList;
    }

    public static PcBuildDTO build(PcBuild pcBuild) {

        String imageUrl = null;

        if(pcBuild.getFileToDatabase() != null)
            imageUrl = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/images/")
                    .path(pcBuild.getFileToDatabase().getId())
                    .toUriString();

        PcBuildPartsListDTO pcBuildPartsListDTO = Optional.ofNullable(pcBuild).map(PcBuildPartsListDTO::build).get();

        UserDetailsDTO userDetailsDTO = Optional.ofNullable(pcBuild.getUserId()).map(UserDetailsDTO::build).get();


        return new PcBuildDTO(
                pcBuild.getId(),
                pcBuild.getBuildName(),
                pcBuild.getBuildDescription(),
                pcBuild.getBuildDate(),
                pcBuild.getCreateDate(),
                userDetailsDTO,
                imageUrl,
                pcBuildPartsListDTO
        );

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getBuildDescription() {
        return buildDescription;
    }

    public void setBuildDescription(String buildDescription) {
        this.buildDescription = buildDescription;
    }

    public Date getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(Date buildDate) {
        this.buildDate = buildDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public UserDetailsDTO getUser() {
        return user;
    }

    public void setUser(UserDetailsDTO user) {
        this.user = user;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public PcBuildPartsListDTO getPcBuildPartsList() {
        return pcBuildPartsList;
    }

    public void setPcBuildPartsList(PcBuildPartsListDTO pcBuildPartsList) {
        this.pcBuildPartsList = pcBuildPartsList;
    }
}
