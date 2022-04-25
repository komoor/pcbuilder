package pl.komoor.pcbuilder.repository.builds;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.komoor.pcbuilder.models.builds.PcBuild;
import pl.komoor.pcbuilder.models.products.*;
import pl.komoor.pcbuilder.models.users.User;

import java.util.List;
import java.util.Optional;

public interface PcBuildRepository extends JpaRepository<PcBuild, Long>, JpaSpecificationExecutor<PcBuild> {

    List<PcBuild> findAll();

    Page<PcBuild> findAll(Specification spec, Pageable pageRequest);

    Page<PcBuild> findByUserId(User id, Pageable pageRequest);

    List<PcBuild> findByUserId(User id);

    Optional<PcBuild> findById(Long id);

    boolean existsById(Long id);

    @Query("SELECT DISTINCT pb.cpuId FROM PcBuild pb")
    List<Cpu> getUsedCpus();

    @Query("SELECT DISTINCT pb.cpuCoolerId FROM PcBuild pb")
    List<CpuCooler> getUsedCpuCoolers();

    @Query("SELECT DISTINCT pb.motherboardId FROM PcBuild pb")
    List<Motherboard> getUsedMotherboards();

    @Query("SELECT DISTINCT pb.gpuId FROM PcBuild pb")
    List<Gpu> getUsedGpus();

    @Query("SELECT DISTINCT pb.psuId FROM PcBuild pb")
    List<Psu> getUsedPsus();

    @Query("SELECT DISTINCT pb.memory FROM PcBuild pb")
    List<Memory> getUsedMemories();

    @Query("SELECT DISTINCT pb.storage FROM PcBuild pb")
    List<Storage> getUsedStorages();

    @Query("SELECT DISTINCT pb.caseId FROM PcBuild pb")
    List<Case> getUsedCases();

}
