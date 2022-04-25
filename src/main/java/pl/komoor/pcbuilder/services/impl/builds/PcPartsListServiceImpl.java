package pl.komoor.pcbuilder.services.impl.builds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.builds.PcPartsList;
import pl.komoor.pcbuilder.models.products.*;
import pl.komoor.pcbuilder.models.users.User;
import pl.komoor.pcbuilder.payload.request.builds.PcPartsListRequest;
import pl.komoor.pcbuilder.repository.builds.PcPartsListRepository;
import pl.komoor.pcbuilder.repository.users.UserRepository;
import pl.komoor.pcbuilder.services.builds.PcPartsListService;
import pl.komoor.pcbuilder.services.products.cases.CaseService;
import pl.komoor.pcbuilder.services.products.cpu.CpuService;
import pl.komoor.pcbuilder.services.products.cpuCooler.CpuCoolerService;
import pl.komoor.pcbuilder.services.products.gpu.GpuService;
import pl.komoor.pcbuilder.services.products.memory.MemoryService;
import pl.komoor.pcbuilder.services.products.motherboard.MotherboardService;
import pl.komoor.pcbuilder.services.products.psu.PsuService;
import pl.komoor.pcbuilder.services.products.storage.StorageService;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class PcPartsListServiceImpl implements PcPartsListService {

    @Autowired
    PcPartsListRepository pcPartsListRepository;

    @Autowired
    UserRepository userRepository;

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
    public void savePcPartsList(PcPartsListRequest pcPartsListRequest) {

        PcPartsList pcPartsList = new PcPartsList();

        Optional<User> user = userRepository.findById(pcPartsListRequest.getUserId());
        Optional<Cpu> cpu = cpuService.findById(pcPartsListRequest.getCpuId());
        Optional<CpuCooler> cpuCooler = cpuCoolerService.findById(pcPartsListRequest.getCpuCoolerId());
        Optional<Motherboard> motherboard = motherboardService.findById(pcPartsListRequest.getMotherboardId());
        Optional<Gpu> gpu = gpuService.findById(pcPartsListRequest.getGpuId());
        Optional<Psu> psu = psuService.findById(pcPartsListRequest.getPsuId());
        Optional<Case> varCase = caseService.findById(pcPartsListRequest.getCaseId());


        List<String> memoryString = pcPartsListRequest.getMemoryId();
        List<Memory> memorySet = new ArrayList<>();

        memoryString.forEach(memoryItem -> {
            Memory memory = memoryService.findById(Long.valueOf(memoryItem))
                    .orElseThrow(() -> new RuntimeException("Memory not found."));
            memorySet.add(memory);
        });

        List<String> storageString = pcPartsListRequest.getStorageId();
        List<Storage> storageSet = new ArrayList<>();

        storageString.forEach(storageItem -> {
            Storage storage = storageService.findById(Long.valueOf(storageItem))
                    .orElseThrow(() -> new RuntimeException("Storage not found."));
            storageSet.add(storage);
        });

        pcPartsList.setUserId(user.get());
        pcPartsList.setCpuId(cpu.get());
        pcPartsList.setCpuCoolerId(cpuCooler.get());
        pcPartsList.setMotherboardId(motherboard.get());
        pcPartsList.setGpuId(gpu.get());
        pcPartsList.setPsuId(psu.get());
        pcPartsList.setCaseId(varCase.get());

        pcPartsList.setMemory(memorySet);
        pcPartsList.setStorage(storageSet);

        pcPartsList.setListName(pcPartsListRequest.getListName());

        pcPartsListRepository.save(pcPartsList);

    }

    @Override
    public List<PcPartsList> findAll() {
        return pcPartsListRepository.findAll();
    }

    @Override
    public Page<PcPartsList> findAll(Pageable pageRequest) {
        return pcPartsListRepository.findAll(pageRequest);
    }

    @Override
    public Page<PcPartsList> findByUserId(User id, Pageable pageRequest) {
        return pcPartsListRepository.findByUserId(id, pageRequest);
    }

    @Override
    public Optional<PcPartsList> findById(Long id) {
        return pcPartsListRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return pcPartsListRepository.existsById(id);
    }

    @Override
    public void deletePcPartsList(PcPartsList pcPartsList) {
        pcPartsListRepository.delete(pcPartsList);
    }
}
