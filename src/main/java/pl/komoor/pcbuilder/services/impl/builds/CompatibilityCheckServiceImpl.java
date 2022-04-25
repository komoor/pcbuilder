package pl.komoor.pcbuilder.services.impl.builds;

import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.productDetails.cpu.CpuSocket;
import pl.komoor.pcbuilder.models.productDetails.motherboard.MotherboardFormFactor;
import pl.komoor.pcbuilder.models.products.*;
import pl.komoor.pcbuilder.payload.request.builds.CompatibilityCheckRequest;
import pl.komoor.pcbuilder.services.builds.CompatibilityCheckService;
import pl.komoor.pcbuilder.services.products.cases.CaseService;
import pl.komoor.pcbuilder.services.products.cpu.CpuService;
import pl.komoor.pcbuilder.services.products.cpuCooler.CpuCoolerService;
import pl.komoor.pcbuilder.services.products.gpu.GpuService;
import pl.komoor.pcbuilder.services.products.memory.MemoryService;
import pl.komoor.pcbuilder.services.products.motherboard.MotherboardService;
import pl.komoor.pcbuilder.services.products.psu.PsuService;
import pl.komoor.pcbuilder.services.products.storage.StorageService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CompatibilityCheckServiceImpl implements CompatibilityCheckService {


    @Autowired
    CpuService cpuService;

    @Autowired
    MotherboardService motherboardService;

    @Autowired
    CpuCoolerService cpuCoolerService;

    @Autowired
    MemoryService memoryService;

    @Autowired
    GpuService gpuService;

    @Autowired
    CaseService caseService;

    @Autowired
    StorageService storageService;

    @Autowired
    PsuService psuService;




    @Override
    public List<String> compatibilityCheck(CompatibilityCheckRequest compatibilityCheckRequest) {

        List<String> compatibilityErrorsList = new ArrayList<>();

        // Cpu - Motherboard

        if(compatibilityCheckRequest.getCpuId() != null && compatibilityCheckRequest.getMotherboardId() != null) {

            Optional<Cpu> cpu = cpuService.findById(compatibilityCheckRequest.getCpuId());
            Optional<Motherboard> motherboard = motherboardService.findById(compatibilityCheckRequest.getMotherboardId());

            if(cpu.isPresent() && motherboard.isPresent())
                compatibilityErrorsList.add(cpuMotherboardCompatibiitySocketCheck(cpu.get(), motherboard.get()));

        }


        // Cpu - CpuCooler

        if(compatibilityCheckRequest.getCpuId() != null && compatibilityCheckRequest.getCpuCoolerId() != null) {

            Optional<Cpu> cpu = cpuService.findById(compatibilityCheckRequest.getCpuId());
            Optional<CpuCooler> cpuCooler = cpuCoolerService.findById(compatibilityCheckRequest.getCpuCoolerId());

            if(cpu.isPresent() && cpuCooler.isPresent())
                compatibilityErrorsList.add(cpuCpuCoolerCompatibilitySocketCheck(cpu.get(), cpuCooler.get()));


        }

        // Motherboard - CpuCooler

        if(compatibilityCheckRequest.getMotherboardId() != null && compatibilityCheckRequest.getCpuCoolerId() != null) {

            Optional<Motherboard> motherboard = motherboardService.findById(compatibilityCheckRequest.getMotherboardId());
            Optional<CpuCooler> cpuCooler = cpuCoolerService.findById(compatibilityCheckRequest.getCpuCoolerId());


            if(cpuCooler.isPresent() && motherboard.isPresent())
                compatibilityErrorsList.add(motherboardCpuCoolerCompatibilitySocketCheck(motherboard.get(), cpuCooler.get()));


        }


        // Motherboard - Memory

        if(compatibilityCheckRequest.getMotherboardId() != null && compatibilityCheckRequest.getMemoryId().stream().findAny().isPresent()) {

            Optional<Motherboard> motherboard = motherboardService.findById(compatibilityCheckRequest.getMotherboardId());

            int numberOfModules = 0;
            int maxMemoryOfModules = 0;

            for (String memoryItem : compatibilityCheckRequest.getMemoryId()) {

                Memory memory = memoryService.findById(Long.valueOf(memoryItem))
                        .orElseThrow(() -> new RuntimeException("Memory not found"));

                compatibilityErrorsList.add(motherboardMemoryCompatibilityMemorySpeedCheck(motherboard.get(), memory));

                numberOfModules += memory.getNumberOfModules();
                maxMemoryOfModules += memory.getMemoryGB() * memory.getNumberOfModules();

            }

            if(motherboard.isPresent()) {
                compatibilityErrorsList.add(motherboardMemoryCompatibilityEmptySlotsCheck(motherboard.get(), numberOfModules));
                compatibilityErrorsList.add(motherboardMemoryCompatibilityMaxMemoryCheck(motherboard.get(), maxMemoryOfModules));
            }

        }

        // Motherboard - Gpu
        if(compatibilityCheckRequest.getMotherboardId() != null && compatibilityCheckRequest.getGpuId() != null) {

            Optional<Motherboard> motherboard = motherboardService.findById(compatibilityCheckRequest.getMotherboardId());
            Optional<Gpu> gpu = gpuService.findById(compatibilityCheckRequest.getGpuId());

            if(gpu.isPresent() && motherboard.isPresent())
                compatibilityErrorsList.add(motherboardGpuCompatibilityInterfaceCheck(motherboard.get(), gpu.get()));


        }

        // Motherboard - Case

        if(compatibilityCheckRequest.getMotherboardId() != null && compatibilityCheckRequest.getCaseId() != null) {

            Optional<Motherboard> motherboard = motherboardService.findById(compatibilityCheckRequest.getMotherboardId());
            Optional<Case> varCase = caseService.findById(compatibilityCheckRequest.getCaseId());

            if(motherboard.isPresent() && varCase.isPresent()) {

                compatibilityErrorsList.add(motherboardCaseCompatibilityMotherboardFormFactorCheck(motherboard.get(), varCase.get()));

            }

        }

        //Motherboard - Storage

        if(compatibilityCheckRequest.getMotherboardId() != null && compatibilityCheckRequest.getStorageId().stream().findAny().isPresent()) {

            Optional<Motherboard> motherboard = motherboardService.findById(compatibilityCheckRequest.getMotherboardId());

            Integer numberOfNmveStorage = 0;
            Integer numberOfSataStorage = 0;


            for (String storageItem : compatibilityCheckRequest.getStorageId()) {

                Storage storage = storageService.findById(Long.valueOf(storageItem))
                        .orElseThrow(() -> new RuntimeException("Storage not found"));

                if(storage.getInterfaceTypeId().getInterfaceName().contains("M.2")) {
                    numberOfNmveStorage++;
                } else if(storage.getInterfaceTypeId().getInterfaceName().contains("SATA")) {
                    numberOfSataStorage++;
                }

            }

            if(motherboard.isPresent()) {
                compatibilityErrorsList.add(motherboardStorageCompatibilityNumberOfNmveModulesCheck(motherboard.get(), numberOfNmveStorage));
                compatibilityErrorsList.add(motherboardStorageCompatibilityNumberOfSataModulesCheck(motherboard.get(), numberOfSataStorage));
            }

        }

        //Case - Storage

        if(compatibilityCheckRequest.getStorageId() != null && compatibilityCheckRequest.getCaseId() != null) {


            Optional<Case> varCase = caseService.findById(compatibilityCheckRequest.getCaseId());

            int numberOfStorage25 = 0;
            int numberOfSataStorage35 = 0;


            for (String storageItem : compatibilityCheckRequest.getStorageId()) {

                Storage storage = storageService.findById(Long.valueOf(storageItem))
                        .orElseThrow(() -> new RuntimeException("Storage not found"));

                if(storage.getStorageFormFactorId().getFormFactorName().contains("2.5")) {
                    numberOfStorage25++;
                } else if(storage.getStorageFormFactorId().getFormFactorName().contains("3.5")) {
                    numberOfSataStorage35++;
                }


            }

            if(varCase.isPresent()) {
                compatibilityErrorsList.add(caseStorageCompatibilityNumberOf25slotsCheck(varCase.get(), numberOfStorage25));
                compatibilityErrorsList.add(caseStorageCompatibilityNumberOf35slotsCheck(varCase.get(), numberOfSataStorage35));
            }

        }


        // psu - case

        if(compatibilityCheckRequest.getPsuId() != null && compatibilityCheckRequest.getCaseId() != null) {

            Optional<Psu> psu = psuService.findById(compatibilityCheckRequest.getPsuId());
            Optional<Case> varCase = caseService.findById(compatibilityCheckRequest.getCaseId());

            if(psu.isPresent() && varCase.isPresent())
                compatibilityErrorsList.add(casePsuCompatibilityFormatCheck(psu.get(), varCase.get()));

        }




        List<String> compatibilityErrorListNonNull = compatibilityErrorsList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return compatibilityErrorListNonNull;
    }

    private String casePsuCompatibilityFormatCheck(Psu psu, Case varCase) {

        boolean errorCompatibility = false;

        if(varCase.getCaseTypeId().getCaseTypeName().equals("ATX Desktop")) {
            if(!(psu.getPsuTypeId().getPsuTypeName().equals("ATX")
                    || psu.getPsuTypeId().getPsuTypeName().equals("BTX")
                    || psu.getPsuTypeId().getPsuTypeName().equals("ATX12V")
                    || psu.getPsuTypeId().getPsuTypeName().equals("EPS12V")
                    || psu.getPsuTypeId().getPsuTypeName().equals("SFX12V")
            ))
                errorCompatibility = true;

        }
        else if (varCase.getCaseTypeId().getCaseTypeName().equals("ATX Full Tower")) {
            if(!(psu.getPsuTypeId().getPsuTypeName().equals("ATX")
                    || psu.getPsuTypeId().getPsuTypeName().equals("BTX")
                    || psu.getPsuTypeId().getPsuTypeName().equals("ATX12V")
                    || psu.getPsuTypeId().getPsuTypeName().equals("EPS12V")
                    || psu.getPsuTypeId().getPsuTypeName().equals("SFX12V")
            ))
                errorCompatibility = true;

        }
        else if (varCase.getCaseTypeId().getCaseTypeName().equals("ATX Mid Tower")) {
            if(!(psu.getPsuTypeId().getPsuTypeName().equals("ATX")
                    || psu.getPsuTypeId().getPsuTypeName().equals("BTX")
                    || psu.getPsuTypeId().getPsuTypeName().equals("ATX12V")
                    || psu.getPsuTypeId().getPsuTypeName().equals("EPS12V")
                    || psu.getPsuTypeId().getPsuTypeName().equals("SFX12V")
            ))
                errorCompatibility = true;
        }
        else if (varCase.getCaseTypeId().getCaseTypeName().equals("ATX Mini Tower")) {
            if(!(psu.getPsuTypeId().getPsuTypeName().equals("ATX")
                    || psu.getPsuTypeId().getPsuTypeName().equals("BTX")
                    || psu.getPsuTypeId().getPsuTypeName().equals("ATX12V")
                    || psu.getPsuTypeId().getPsuTypeName().equals("EPS12V")
                    || psu.getPsuTypeId().getPsuTypeName().equals("SFX12V")
            ))
                errorCompatibility = true;
        }
        else if (varCase.getCaseTypeId().getCaseTypeName().equals("ATX Test Bench")) {
            if(!(psu.getPsuTypeId().getPsuTypeName().equals("ATX")
                    || psu.getPsuTypeId().getPsuTypeName().equals("BTX")
                    || psu.getPsuTypeId().getPsuTypeName().equals("ATX12V")
                    || psu.getPsuTypeId().getPsuTypeName().equals("EPS12V")
                    || psu.getPsuTypeId().getPsuTypeName().equals("SFX12V")
            ))
                errorCompatibility = true;
        } else if (varCase.getCaseTypeId().getCaseTypeName().equals("HTPC")) {
            if(!psu.getPsuTypeId().getPsuTypeName().equals("SFX12V"))
                errorCompatibility = true;
        } else if (varCase.getCaseTypeId().getCaseTypeName().equals("MicroATX Desktop")) {
            if(!(psu.getPsuTypeId().getPsuTypeName().equals("ATX")
                    || psu.getPsuTypeId().getPsuTypeName().equals("BTX")
                    || psu.getPsuTypeId().getPsuTypeName().equals("ATX12V")
                    || psu.getPsuTypeId().getPsuTypeName().equals("EPS12V")
                    || psu.getPsuTypeId().getPsuTypeName().equals("SFX12V")
            ))
                errorCompatibility = true;
        } else if (varCase.getCaseTypeId().getCaseTypeName().equals("MicroATX Mid Tower")) {
            if(!(psu.getPsuTypeId().getPsuTypeName().equals("ATX")
                    || psu.getPsuTypeId().getPsuTypeName().equals("BTX")
                    || psu.getPsuTypeId().getPsuTypeName().equals("ATX12V")
                    || psu.getPsuTypeId().getPsuTypeName().equals("EPS12V")
                    || psu.getPsuTypeId().getPsuTypeName().equals("SFX12V")
            ))
                errorCompatibility = true;
        } else if (varCase.getCaseTypeId().getCaseTypeName().equals("MicroATX Mini Tower")) {
            if(!(psu.getPsuTypeId().getPsuTypeName().equals("ATX")
                    || psu.getPsuTypeId().getPsuTypeName().equals("BTX")
                    || psu.getPsuTypeId().getPsuTypeName().equals("ATX12V")
                    || psu.getPsuTypeId().getPsuTypeName().equals("EPS12V")
                    || psu.getPsuTypeId().getPsuTypeName().equals("SFX12V")
            ))
                errorCompatibility = true;
        } else if (varCase.getCaseTypeId().getCaseTypeName().equals("MicroATX Slim")) {
            if(!psu.getPsuTypeId().getPsuTypeName().equals("Micro ATX"))
                errorCompatibility = true;
        } else if (varCase.getCaseTypeId().getCaseTypeName().equals("Mini ITX Desktop")) {
            if(!(psu.getPsuTypeId().getPsuTypeName().equals("ATX")
                    || psu.getPsuTypeId().getPsuTypeName().equals("BTX")
                    || psu.getPsuTypeId().getPsuTypeName().equals("ATX12V")
                    || psu.getPsuTypeId().getPsuTypeName().equals("EPS12V")
                    || psu.getPsuTypeId().getPsuTypeName().equals("SFX12V")
            ))
                errorCompatibility = true;
        } else if (varCase.getCaseTypeId().getCaseTypeName().equals("Mini ITX Test Bench")) {
            if(!(psu.getPsuTypeId().getPsuTypeName().equals("ATX")
                    || psu.getPsuTypeId().getPsuTypeName().equals("BTX")
                    || psu.getPsuTypeId().getPsuTypeName().equals("ATX12V")
                    || psu.getPsuTypeId().getPsuTypeName().equals("EPS12V")
                    || psu.getPsuTypeId().getPsuTypeName().equals("SFX12V")
            ))
                errorCompatibility = true;
        } else if (varCase.getCaseTypeId().getCaseTypeName().equals("Mini ITX Tower")) {
            if(!(psu.getPsuTypeId().getPsuTypeName().equals("ATX")
                    || psu.getPsuTypeId().getPsuTypeName().equals("BTX")
                    || psu.getPsuTypeId().getPsuTypeName().equals("ATX12V")
                    || psu.getPsuTypeId().getPsuTypeName().equals("EPS12V")
                    || psu.getPsuTypeId().getPsuTypeName().equals("SFX12V")
            ))
                errorCompatibility = true;
        }



        if(errorCompatibility)
        return psu.getManufacturerId().getManufacturerName() + " " + varCase.getModel() + ": " + "Format zasilacza ["  + psu.getPsuTypeId().getPsuTypeName() + "] jest niezgodny z formatem obudowy [" + varCase.getCaseTypeId().getCaseTypeName() + "]";


        return null;

    }


    private String caseStorageCompatibilityNumberOf35slotsCheck(Case varCase, Integer numberOfSataStorage35) {

        int numberOf35slotsExAndIn = varCase.getEx35bays() + varCase.getIn35bays();

        if(numberOf35slotsExAndIn < numberOfSataStorage35)
            return varCase.getManufacturerId().getManufacturerName() + " " + varCase.getModel() + ": " + "Wybrana ilość dysków 3.5\" ["  + numberOfSataStorage35 + "] przekracza maksymalną dostępną ilość dostępnych zatok 3.5\" obudowy [" + numberOf35slotsExAndIn + "]";

        return null;

    }


    private String caseStorageCompatibilityNumberOf25slotsCheck(Case varCase, Integer numberOfStorage25) {

        int numberOf25slotsExAndIn = varCase.getEx25bays() + varCase.getIn25bays();

        if(numberOf25slotsExAndIn < numberOfStorage25)
            return varCase.getManufacturerId().getManufacturerName() + " " + varCase.getModel() + ": " + "Wybrana ilość dysków 2.5\" ["  + numberOfStorage25 + "] przekracza maksymalną dostępną ilość dostępnych zatok 2.5\" obudowy [" + numberOf25slotsExAndIn + "]";

        return null;

    }


    private String motherboardStorageCompatibilityNumberOfSataModulesCheck(Motherboard motherboard, Integer numberOfSataStorage) {

        int numberOfMotherboardSata = motherboard.getSata3GbPorts() + motherboard.getSata6GbPorts();

        if(numberOfMotherboardSata < numberOfSataStorage)
            return motherboard.getManufacturerId().getManufacturerName() + " " + motherboard.getModel() + ": " + "Wybrana ilość dysków SATA ["  + numberOfSataStorage + "] przekracza maksymalną dostępną ilość slotów na płycie głównej [" + numberOfMotherboardSata + "]";

        return null;

    }

    private String motherboardStorageCompatibilityNumberOfNmveModulesCheck(Motherboard motherboard, Integer numberOfNmveStorage) {

        if(motherboard.getM2Slots() < numberOfNmveStorage)
            return motherboard.getManufacturerId().getManufacturerName() + " " + motherboard.getModel() + ": " + "Wybrana ilość dysków NMVE ["  + numberOfNmveStorage + "] przekracza maksymalną dostępną ilość slotów na płycie głównej [" + motherboard.getM2Slots() + "]";

        return null;
    }


    private String motherboardCpuCoolerCompatibilitySocketCheck(Motherboard motherboard, CpuCooler cpuCooler) {

        if(!cpuCooler.getSocketId().contains(motherboard.getCpuSocketId()))
            return "Niekompatybilny socket: " + cpuCooler.getSocketId().stream().map(CpuSocket::getSocketName).collect(Collectors.joining(", ")) + " [Chłodzenie CPU]" + " - " + motherboard.getCpuSocketId().getSocketName() + " [Płyta główna]";

        return null;


    }


    public String cpuMotherboardCompatibiitySocketCheck(Cpu cpu, Motherboard motherboard) {
                          
        if(!cpu.getSocketId().equals(motherboard.getCpuSocketId()))
            return "Niekompatybilny socket: " + cpu.getSocketId().getSocketName() + " [Procesor]" + " - " + motherboard.getCpuSocketId().getSocketName() + " [Płyta główna]";

        return null;

    }

    public String cpuCpuCoolerCompatibilitySocketCheck(Cpu cpu, CpuCooler cpuCooler) {

        if(!cpuCooler.getSocketId().contains(cpu.getSocketId()))
            return "Niekompatybilny socket: " + cpu.getSocketId().getSocketName() + " [Procesor]" + " - " + cpuCooler.getSocketId().stream().map(CpuSocket::getSocketName).collect(Collectors.joining(", ")) + " [Chłodzenie CPU]";

        return null;
    }

    public String motherboardMemoryCompatibilityMemorySpeedCheck(Motherboard motherboard, Memory memory) {

        if(!motherboard.getMemoryTypeId().contains(memory.getMemoryTypeId()))
            return motherboard.getManufacturerId().getManufacturerName() + " " + motherboard.getModel() + " - " + memory.getManufacturerId().getManufacturerName() + " " + memory.getModel() + ": " + " płyta główna nie obsługuje pamięci o typie: " + memory.getMemoryTypeId().getTypeName();

            return null;
    }

    public String motherboardMemoryCompatibilityEmptySlotsCheck(Motherboard motherboard, Integer numberOfModules) {

        if(motherboard.getMemorySlots() < numberOfModules)
            return motherboard.getManufacturerId().getManufacturerName() + " " + motherboard.getModel() + ": " + "Wybrana ilość modułów pamięci ["  + numberOfModules + "] przekracza maksymalną dostępną ilość slotów na płycie głównej [" + motherboard.getMemorySlots() + "]";

        return null;
    }

    public String motherboardMemoryCompatibilityMaxMemoryCheck(Motherboard motherboard, Integer maxMemoryOfModules) {

        if(motherboard.getMemoryMax() < maxMemoryOfModules)
            return motherboard.getManufacturerId().getManufacturerName() + " " + motherboard.getModel() + ": " + "Sumaryczna ilość pamięci wybranych modułów pamięci ["  + maxMemoryOfModules + " GB] przekracza maksymalną dostępną ilość obsługiwaną przez płytę główną [" + motherboard.getMemoryMax() + " GB]";

        return null;
    }

    public String motherboardGpuCompatibilityInterfaceCheck(Motherboard motherboard, Gpu gpu) {

        if(motherboard.getPcieX16Slots() == 0 && gpu.getInterfaceTypeId().getInterfaceName().equals("PCIe x16"))
            return motherboard.getManufacturerId().getManufacturerName() + " " + motherboard.getModel() + ": " + "Płyta główna nie posiada wystarczającej ilości slotów: " + gpu.getInterfaceTypeId().getInterfaceName() + " do zamontowania karty graficznej " + gpu.getManufacturerId().getManufacturerName() + " " + gpu.getModel();

        if(motherboard.getPcieX8Slots() == 0 && gpu.getInterfaceTypeId().getInterfaceName().equals("PCIe x8"))
            return motherboard.getManufacturerId().getManufacturerName() + " " + motherboard.getModel() + ": " + "Płyta główna nie posiada wystarczającej ilości slotów: " + gpu.getInterfaceTypeId().getInterfaceName() + " do zamontowania karty graficznej " + gpu.getManufacturerId().getManufacturerName() + " " + gpu.getModel();

        if(motherboard.getPcieX4Slots() == 0 && gpu.getInterfaceTypeId().getInterfaceName().equals("PCIe x4"))
            return motherboard.getManufacturerId().getManufacturerName() + " " + motherboard.getModel() + ": " + "Płyta główna nie posiada wystarczającej ilości slotów: " + gpu.getInterfaceTypeId().getInterfaceName() + " do zamontowania karty graficznej " + gpu.getManufacturerId().getManufacturerName() + " " + gpu.getModel();

        if(motherboard.getPcieX1Slots() == 0 && gpu.getInterfaceTypeId().getInterfaceName().equals("PCIe x1"))
            return motherboard.getManufacturerId().getManufacturerName() + " " + motherboard.getModel() + ": " + "Płyta główna nie posiada wystarczającej ilości slotów: " + gpu.getInterfaceTypeId().getInterfaceName() + " do zamontowania karty graficznej " + gpu.getManufacturerId().getManufacturerName() + " " + gpu.getModel();


        return null;
    }

    public String motherboardCaseCompatibilityMotherboardFormFactorCheck(Motherboard motherboard, Case varCase) {

        if(!varCase.getMotherboardFormFactorId().contains(motherboard.getMotherboardFormFactorId()))
            return "Niekompatybilny format płyty głównej: " + motherboard.getMotherboardFormFactorId().getMotherboardFormFactorName() + " [Płyta główna]" + " - " + varCase.getMotherboardFormFactorId().stream().map(MotherboardFormFactor::getMotherboardFormFactorName).collect(Collectors.joining(", ")) + " [Obudowa]";

        return null;
    }
}
