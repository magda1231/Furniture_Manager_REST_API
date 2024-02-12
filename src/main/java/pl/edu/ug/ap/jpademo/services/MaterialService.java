package pl.edu.ug.ap.jpademo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.ug.ap.jpademo.domain.Material;
import pl.edu.ug.ap.jpademo.dto.FurniturePieceDto;
import pl.edu.ug.ap.jpademo.repository.MaterialRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class MaterialService {
    private final MaterialRepository materialRepository;

    @Autowired
    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }
    public boolean existsById(Long materialId) {
        return materialRepository.existsById(materialId);
    }
    public Set<Material> findAllByIds(Set<Long> materialIds) {
        Set <Material> materials = new HashSet<>();
        for (Long id : materialIds) {
            materialRepository.findById(id).ifPresent(materials::add);
        }
        return materials;
    }

    public Optional<Material> findByName(String name) {
        return Optional.ofNullable(materialRepository.findByName(name));
    }

    public Material addMaterial(Material material){
        return materialRepository.save(material);
    }


}