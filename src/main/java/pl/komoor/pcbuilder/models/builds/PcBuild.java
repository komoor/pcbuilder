package pl.komoor.pcbuilder.models.builds;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import pl.komoor.pcbuilder.models.products.*;
import pl.komoor.pcbuilder.models.tools.FileToDatabase;
import pl.komoor.pcbuilder.models.users.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(	name = "pc_build")
public class PcBuild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date buildDate;

    @NotBlank
    @Size(max = 50)
    private String buildName;

    @NotBlank
    @Size(max = 1000)
    private String buildDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cpuId", referencedColumnName = "id", nullable=false)
    private Cpu cpuId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cpuCoolerId", referencedColumnName = "id", nullable=false)
    private CpuCooler cpuCoolerId ;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="motherboardId", referencedColumnName = "id", nullable=false)
    private Motherboard motherboardId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="psuId", referencedColumnName = "id", nullable=false)
    private Psu psuId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="gpuId", referencedColumnName = "id", nullable=false)
    private Gpu gpuId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="caseId", referencedColumnName = "id", nullable=false)
    private Case caseId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pc_build_memory", joinColumns = @JoinColumn(name = "pc_build_id"),
            inverseJoinColumns = @JoinColumn(name = "memory_id"))
    private List<Memory> memory;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pc_build_storage", joinColumns = @JoinColumn(name = "pc_build_id"),
            inverseJoinColumns = @JoinColumn(name = "storage_id"))
    private List<Storage> storage;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name="imageId", nullable = true, referencedColumnName = "id", updatable = true)
    private FileToDatabase fileToDatabase;

    @OneToMany(mappedBy = "pcBuildId", orphanRemoval = true, cascade = CascadeType.REMOVE, targetEntity = PcBuildReview.class)
    private List<PcBuildReview> pcBuildReviews;

    public PcBuild() {
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

    public Date getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(Date buildDate) {
        this.buildDate = buildDate;
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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Cpu getCpuId() {
        return cpuId;
    }

    public void setCpuId(Cpu cpuId) {
        this.cpuId = cpuId;
    }

    public CpuCooler getCpuCoolerId() {
        return cpuCoolerId;
    }

    public void setCpuCoolerId(CpuCooler cpuCoolerId) {
        this.cpuCoolerId = cpuCoolerId;
    }

    public Motherboard getMotherboardId() {
        return motherboardId;
    }

    public void setMotherboardId(Motherboard motherboardId) {
        this.motherboardId = motherboardId;
    }

    public Psu getPsuId() {
        return psuId;
    }

    public void setPsuId(Psu psuId) {
        this.psuId = psuId;
    }

    public Gpu getGpuId() {
        return gpuId;
    }

    public void setGpuId(Gpu gpuId) {
        this.gpuId = gpuId;
    }

    public Case getCaseId() {
        return caseId;
    }

    public void setCaseId(Case caseId) {
        this.caseId = caseId;
    }

    public List<Memory> getMemory() {
        return memory;
    }

    public void setMemory(List<Memory> memory) {
        this.memory = memory;
    }

    public List<Storage> getStorage() {
        return storage;
    }

    public void setStorage(List<Storage> storage) {
        this.storage = storage;
    }

    public FileToDatabase getFileToDatabase() {
        return fileToDatabase;
    }

    public void setFileToDatabase(FileToDatabase fileToDatabase) {
        this.fileToDatabase = fileToDatabase;
    }
}
