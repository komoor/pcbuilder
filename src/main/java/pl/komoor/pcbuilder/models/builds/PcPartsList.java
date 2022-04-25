package pl.komoor.pcbuilder.models.builds;

import org.hibernate.annotations.CreationTimestamp;
import pl.komoor.pcbuilder.models.products.*;
import pl.komoor.pcbuilder.models.users.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(	name = "pc_parts_list")
public class PcPartsList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @NotBlank
    @Size(max = 50)
    private String listName;

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
    private Case CaseId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pc_parts_list_memory", joinColumns = @JoinColumn(name = "pc_parts_list_id"),
            inverseJoinColumns = @JoinColumn(name = "memory_id"))
    private List<Memory> memory;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pc_parts_list_storage", joinColumns = @JoinColumn(name = "pc_parts_list_id"),
            inverseJoinColumns = @JoinColumn(name = "storage_id"))
    private List<Storage> storage;

    public PcPartsList() {
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
        return CaseId;
    }

    public void setCaseId(Case caseId) {
        CaseId = caseId;
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


}
