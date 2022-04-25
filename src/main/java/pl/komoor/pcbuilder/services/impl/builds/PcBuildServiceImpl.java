package pl.komoor.pcbuilder.services.impl.builds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.builds.PcBuild;
import pl.komoor.pcbuilder.models.products.*;
import pl.komoor.pcbuilder.models.tools.FileToDatabase;
import pl.komoor.pcbuilder.models.users.User;
import pl.komoor.pcbuilder.payload.request.builds.PcBuildRequest;
import pl.komoor.pcbuilder.repository.builds.PcBuildRepository;
import pl.komoor.pcbuilder.repository.users.UserRepository;
import pl.komoor.pcbuilder.services.builds.PcBuildService;
import pl.komoor.pcbuilder.services.builds.PcPartsListService;
import pl.komoor.pcbuilder.services.dto.builds.PcBuildFilterDTO;
import pl.komoor.pcbuilder.services.products.cases.CaseService;
import pl.komoor.pcbuilder.services.products.cpu.CpuService;
import pl.komoor.pcbuilder.services.products.cpuCooler.CpuCoolerService;
import pl.komoor.pcbuilder.services.products.gpu.GpuService;
import pl.komoor.pcbuilder.services.products.memory.MemoryService;
import pl.komoor.pcbuilder.services.products.motherboard.MotherboardService;
import pl.komoor.pcbuilder.services.products.psu.PsuService;
import pl.komoor.pcbuilder.services.products.storage.StorageService;
import pl.komoor.pcbuilder.services.tools.FileToDatabaseService;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PcBuildServiceImpl implements PcBuildService {

    @Autowired
    PcBuildRepository pcBuildRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PcPartsListService pcPartsListService;

    @Autowired
    FileToDatabaseService fileToDatabaseService;

    @Autowired
    CpuService cpuService;

    @Autowired
    CpuCoolerService cpuCoolerService;

    @Autowired
    MotherboardService motherboardService;

    @Autowired
    PsuService psuService;

    @Autowired
    GpuService gpuService;

    @Autowired
    StorageService storageService;

    @Autowired
    CaseService caseService;

    @Autowired
    MemoryService memoryService;

    @Override
    @Transactional
    public void savePcBuild(PcBuildRequest pcBuildRequest) {

        PcBuild pcBuild = new PcBuild();

        Optional<User> user = userRepository.findById(pcBuildRequest.getUserId());
        Optional<Cpu> cpu = cpuService.findById(pcBuildRequest.getCpuId());
        Optional<CpuCooler> cpuCooler = cpuCoolerService.findById(pcBuildRequest.getCpuCoolerId());
        Optional<Motherboard> motherboard = motherboardService.findById(pcBuildRequest.getMotherboardId());
        Optional<Gpu> gpu = gpuService.findById(pcBuildRequest.getGpuId());
        Optional<Psu> psu = psuService.findById(pcBuildRequest.getPsuId());
        Optional<Case> varCase = caseService.findById(pcBuildRequest.getCaseId());


        List<String> memoryString = pcBuildRequest.getMemoryId();
        List<Memory> memorySet = new ArrayList<>();

        memoryString.forEach(memoryItem -> {
            Memory memory = memoryService.findById(Long.valueOf(memoryItem))
                    .orElseThrow(() -> new RuntimeException("Memory not found."));
            memorySet.add(memory);
        });

        List<String> storageString = pcBuildRequest.getStorageId();
        List<Storage> storageSet = new ArrayList<>();

        storageString.forEach(storageItem -> {
            Storage storage = storageService.findById(Long.valueOf(storageItem))
                    .orElseThrow(() -> new RuntimeException("Storage not found."));
            storageSet.add(storage);
        });

        if(pcBuildRequest.getImageId() == null) {
            pcBuild.setFileToDatabase(null);
        } else {

            Optional<FileToDatabase> fileToDatabase = fileToDatabaseService.findById(pcBuildRequest.getImageId());

            if(fileToDatabase.isPresent()) {
                fileToDatabase.ifPresent(pcBuild::setFileToDatabase);
            } else
            {
                pcBuild.setFileToDatabase(null);
            }
        }

        user.ifPresent(pcBuild::setUserId);
        pcBuild.setBuildName(pcBuildRequest.getBuildName());
        pcBuild.setBuildDescription(pcBuildRequest.getBuildDescription());
        pcBuild.setBuildDate(pcBuildRequest.getBuildDate());

        pcBuild.setUserId(user.get());
        pcBuild.setCpuId(cpu.get());
        pcBuild.setCpuCoolerId(cpuCooler.get());
        pcBuild.setMotherboardId(motherboard.get());
        pcBuild.setGpuId(gpu.get());
        pcBuild.setPsuId(psu.get());
        pcBuild.setCaseId(varCase.get());

        pcBuild.setMemory(memorySet);
        pcBuild.setStorage(storageSet);

        pcBuildRepository.save(pcBuild);

    }

    @Override
    public Page<PcBuild> findAll(Specification spec, Pageable pageRequest) {
        return pcBuildRepository.findAll(spec, pageRequest);
    }

    @Override
    public Page<PcBuild> findByUserId(User id, Pageable pageRequest) {
        return pcBuildRepository.findByUserId(id, pageRequest);
    }

    @Override
    public List<PcBuild> findByUserId(User id) {
        return pcBuildRepository.findByUserId(id);
    }

    @Override
    public Optional<PcBuild> findById(Long id) {
        return pcBuildRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return pcBuildRepository.existsById(id);
    }

    @Override
    public void deletePcBuild(PcBuild pcBuild) {

        pcBuildRepository.delete(pcBuild);

    }

    @Override
    public PcBuildFilterDTO filterList() {

        List<Cpu> cpus = pcBuildRepository.getUsedCpus();
        List<CpuCooler> cpuCoolers = pcBuildRepository.getUsedCpuCoolers();
        List<Motherboard> motherboards = pcBuildRepository.getUsedMotherboards();
        List<Gpu> gpus = pcBuildRepository.getUsedGpus();
        List<Psu> psus = pcBuildRepository.getUsedPsus();
        List<Memory> memories = pcBuildRepository.getUsedMemories();
        List<Storage> storages = pcBuildRepository.getUsedStorages();
        List<Case> cases = pcBuildRepository.getUsedCases();

        PcBuildFilterDTO pcBuildFilterDTO = PcBuildFilterDTO.build(cpus, cpuCoolers, motherboards, gpus, psus, memories, storages, cases);

        return pcBuildFilterDTO;


    }
}
