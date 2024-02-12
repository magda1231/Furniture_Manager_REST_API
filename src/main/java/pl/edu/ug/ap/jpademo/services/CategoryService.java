package pl.edu.ug.ap.jpademo.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.edu.ug.ap.jpademo.domain.Category;
import pl.edu.ug.ap.jpademo.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public boolean existsById(Long categoryId) {return categoryRepository.existsById(categoryId);}
    public Optional<Category> findById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }
    public Optional<Category> findByName(String name) {
       return Optional.ofNullable(categoryRepository.findByName(name));
    }
    public List<Category> listAll(){
        System.out.println("Listing all categories");
        return (List<Category>) categoryRepository.findAll();
    }
    public List<Category> listAllWithFurniturePieces() {
        return categoryRepository.findAllWithFurniturePieces();
    }
    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}