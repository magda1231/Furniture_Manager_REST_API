package pl.edu.ug.ap.jpademo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.edu.ug.ap.jpademo.domain.Material;

public interface MaterialRepository extends CrudRepository<Material, Long> {

    @Query("SELECT m FROM Material m WHERE m.name = :name")
    Material findByName(String name);

}
