package pl.edu.ug.ap.jpademo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.edu.ug.ap.jpademo.domain.Category;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findAll();

    @Query("SELECT c FROM Category c WHERE c.name = :name")
    Category findByName(String name);

    @Query("SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.furniturePieces")
    List<Category> findAllWithFurniturePieces();
}
