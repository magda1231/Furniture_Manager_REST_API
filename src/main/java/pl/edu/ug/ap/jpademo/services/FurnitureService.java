package pl.edu.ug.ap.jpademo.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.edu.ug.ap.jpademo.domain.Category;
import pl.edu.ug.ap.jpademo.domain.Dimensions;
import pl.edu.ug.ap.jpademo.domain.FurniturePiece;
import pl.edu.ug.ap.jpademo.domain.Material;
import pl.edu.ug.ap.jpademo.repository.FurnitureRepository;
import pl.edu.ug.ap.jpademo.repository.CategoryRepository;
import pl.edu.ug.ap.jpademo.repository.MaterialRepository;
import pl.edu.ug.ap.jpademo.dto.FurniturePieceDto;

import java.time.LocalDate;
import java.util.*;
@Service
@Transactional
public class FurnitureService {
    final FurnitureRepository furnitureRepository;
    final CategoryRepository categoryRepository;
    final MaterialRepository materialRepository;
    final MaterialService materialService;
    final CategoryService categoryService;

    public FurnitureService(FurnitureRepository furnitureRepository, CategoryRepository categoryRepository, MaterialRepository materialRepository,
                            MaterialService materialService, CategoryService categoryService
    ) {
        this.furnitureRepository = furnitureRepository;
        this.categoryRepository = categoryRepository;
        this.materialRepository = materialRepository;
        this.materialService = materialService;
        this.categoryService = categoryService;
    }
    public FurniturePiece addFurniturePiece(FurniturePieceDto piece){
        FurniturePiece furniturePiece = new FurniturePiece();
        furniturePiece.setName(piece.getName());
        furniturePiece.setPrice(piece.getPrice());
        Optional<Category> category = categoryService.findById(Long.parseLong(piece.getCategory()));
        if (category.isEmpty()) {
            Category newCategory = new Category();
            categoryService.addCategory(newCategory);
            newCategory.setName(piece.getCategory());
            furniturePiece.setCategory(newCategory);
        } else {
            furniturePiece.setCategory(category.get());
        }


        Set<Material> materials = new HashSet<>();
        for (String material : piece.getMaterials()) {
            Optional<Material> materialOptional = materialService.findByName(material);
            if (materialOptional.isEmpty()) {
                Material newMaterial = new Material(material);
                materialService.addMaterial(newMaterial);
                materials.add(newMaterial);
            } else {
                materials.add(materialOptional.get());
            }
        }

        furniturePiece.setDate(piece.getDate());
        furniturePiece.setMaterials(materials);
        System.out.println(furniturePiece.toString());
        System.out.println("aaaaaaaa");
       return furnitureRepository.save(furniturePiece);
    }

    public Optional<FurniturePieceDto> findFurniturePiece(String id) {
        Long id_long = Long.parseLong(id);
       Optional<FurniturePiece> furniturePiece = furnitureRepository.findById(id_long);
          if (furniturePiece.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(changeToDto(furniturePiece.get()));

    }


    public List<FurniturePieceDto> listAll(){
        List<FurniturePiece> list = furnitureRepository.findAll();
        return list.stream().map(this::changeToDto).toList();

    }
    public Optional<FurniturePiece> findFurniturePieceByName(String name) {
        return furnitureRepository.findByName(name);
    }
    public void deleteFurniturePiece(long id){
        furnitureRepository.deleteById(id);
    }

     public boolean existsById(Long id) {
          return furnitureRepository.existsById(id);

}

    public void updateFurniturePiece(Long id, FurniturePieceDto piece) {
        FurniturePiece furniturePiece = new FurniturePiece();
        furniturePiece.setName(piece.getName());
        furniturePiece.setId(id);
        furniturePiece.setPrice(piece.getPrice());
        Optional<Category> category = categoryService.findById(id);
        if (category.isEmpty()) {
            Category newCategory = new Category();
            categoryService.addCategory(newCategory);
            newCategory.setName(piece.getCategory());
            furniturePiece.setCategory(newCategory);
        } else {
            furniturePiece.setCategory(category.get());
        }
        Set<Material> materials = new HashSet<>();
        for (String material : piece.getMaterials()) {
            Optional<Material> materialOptional = materialService.findByName(material);
            if (materialOptional.isEmpty()) {
                Material newMaterial = new Material(material);
                materialService.addMaterial(newMaterial);
                materials.add(newMaterial);
            } else {
                materials.add(materialOptional.get());
            }
        }
        furnitureRepository.save(furniturePiece);
    }

    public FurniturePieceDto changeToDto(FurniturePiece furniturePiece) {
        FurniturePieceDto furniturePieceDto = new FurniturePieceDto();
        furniturePieceDto.setId(furniturePiece.getId());
        furniturePieceDto.setName(furniturePiece.getName());
        furniturePieceDto.setPrice(furniturePiece.getPrice());
        furniturePieceDto.setCategory(furniturePiece.getCategory().getName());
        furniturePieceDto.setDate(furniturePiece.getDate());
        List<String> materials = new ArrayList<>();
        for (Material material : furniturePiece.getMaterials()) {
            materials.add(material.getName());
        }
        furniturePieceDto.setMaterials(materials);
        return furniturePieceDto;
    }

    public FurniturePiece changeToEntity(FurniturePieceDto furniturePieceDto) {
        FurniturePiece furniturePiece = new FurniturePiece();
        furniturePiece.setId(furniturePieceDto.getId());
        furniturePiece.setName(furniturePieceDto.getName());
        furniturePiece.setPrice(furniturePieceDto.getPrice());
        furniturePiece.setCategory(categoryService.findByName(furniturePieceDto.getCategory()).get());
        furniturePiece.setDate(furniturePieceDto.getDate());
        Set<Material> materials = new HashSet<>();
        for (String material : furniturePieceDto.getMaterials()) {
            materials.add(materialService.findByName(material).get());
        }
        furniturePiece.setMaterials(materials);
        return furniturePiece;
    }


//    public List<?> searchFurniturePieces(String name, String category, String material, Double priceFrom, Double priceTo, Double widthFrom, Double widthTo, Double heightFrom, Double heightTo, Double depthFrom, Double depthTo) {
//        List<?> furniturepieces = new ArrayList<>();
//        // add all parameters to arrayList
//        List<String> parameters = new ArrayList<>();
//        parameters.add(name);
//        parameters.add(category);
//        parameters.add(material);
//        parameters.add(priceFrom.toString());
//        parameters.add(priceTo.toString());
//        parameters.add(widthFrom.toString());
//        parameters.add(widthTo.toString());
//        parameters.add(heightFrom.toString());
//        parameters.add(heightTo.toString());
//        parameters.add(depthFrom.toString());
//        parameters.add(depthTo.toString());
//        // remove all nulls
////        parameters.removeAll(Collections.singleton(null));
//        return furniturepieces;
//
//    }

    public Category searchCategoryFurniturePieces(String category) {
        return categoryRepository.findByName(category);
    }

        public void init() {
            // add 5 furniture pieces
            // add categories
            Category furniture = new Category("furniture");
            Category kitchen = new Category("Kitchen");
            Category bathroom = new Category("Bathroom");
            categoryRepository.save(furniture);
            categoryRepository.save(kitchen);
            categoryRepository.save(bathroom);

            FurniturePiece furniturePiece1 = new FurniturePiece("Chair", 50, furniture, new HashSet<Material>(Arrays.asList(new Material("wood"), new Material("metal"))), LocalDate.now());
            FurniturePiece furniturePiece2 = new FurniturePiece("Sink", 200, bathroom, new HashSet<Material>(Arrays.asList(new Material("ceramic"))), LocalDate.now());

            furnitureRepository.save(furniturePiece1);
            furnitureRepository.save(furniturePiece2);
//            this.addFurniturePiece(changeToDto(furniturePiece3));
//            this.addFurniturePiece(changeToDto(furniturePiece4));
//            this.addFurniturePiece(changeToDto(furniturePiece5));
//            this.addFurniturePiece(changeToDto(furniturePiece6));
//            this.addFurniturePiece(changeToDto(furniturePiece7));
//            this.addFurniturePiece(changeToDto(furniturePiece8));




        }
    }
