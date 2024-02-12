package pl.edu.ug.ap.jpademo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.edu.ug.ap.jpademo.domain.FurniturePiece;

import java.util.List;
import java.util.Optional;


public interface FurnitureRepository extends CrudRepository<FurniturePiece, Long> {
    List<FurniturePiece> findAll();

    @Query("SELECT f FROM FurniturePiece f WHERE f.name = :name")
    Optional<FurniturePiece> findByName(String name);

    @Query("SELECT f FROM FurniturePiece f WHERE f.category.name = :categoryId")
    List<FurniturePiece> findAllByCategoryId(String categoryId);

}
