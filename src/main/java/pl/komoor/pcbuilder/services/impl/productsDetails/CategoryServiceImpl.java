package pl.komoor.pcbuilder.services.impl.productsDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.komoor.pcbuilder.models.productDetails.Category;
import pl.komoor.pcbuilder.models.productDetails.cpu.CpuSocket;
import pl.komoor.pcbuilder.repository.productsDetails.CategoryRepository;
import pl.komoor.pcbuilder.services.dto.productsDetails.CategoryDTO;
import pl.komoor.pcbuilder.services.productsDetails.CategoryService;
import pl.komoor.pcbuilder.services.productsDetails.cpu.CpuSocketService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CpuSocketService cpuSocketService;

    @Override
    public List<CategoryDTO> findAll() {

        List<CpuSocket> cpus = cpuSocketService.findAll();

        return categoryRepository.findAll().stream().map(CategoryDTO::build).collect(Collectors.toList());
    }

    @Override
    public Optional<Category> findByCategoryName(String categoryName) {

        Optional<Category> category = categoryRepository.findByCategoryName(categoryName);

        return category;
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }
}
