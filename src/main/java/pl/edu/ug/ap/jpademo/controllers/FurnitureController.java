package pl.edu.ug.ap.jpademo.controllers;

import org.hibernate.mapping.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.ug.ap.jpademo.domain.*;
import pl.edu.ug.ap.jpademo.dto.FurniturePieceDto;
import pl.edu.ug.ap.jpademo.services.CategoryService;
import pl.edu.ug.ap.jpademo.services.FurnitureService;
import org.springframework.http.ResponseEntity;

import java.util.*;

@RestController
@RequestMapping("/furniture")
@CrossOrigin(origins = "http://localhost:4200")
public class FurnitureController {
    private final FurnitureService furnitureService;

    public FurnitureController(FurnitureService furnitureService, CategoryService categoryService) {
        this.furnitureService = furnitureService;

    }

    @GetMapping
    public ResponseEntity<List<FurniturePieceDto>> getFurniturePieces() {
        List<FurniturePieceDto> list = furnitureService.listAll();
        return ResponseEntity.ok(list);

    };

    @GetMapping("{id}")
    public ResponseEntity<Optional<FurniturePieceDto>> getFurniturePiece(@PathVariable String id) {
       if(furnitureService.existsById(Long.parseLong(id))) {

           Optional<FurniturePieceDto> piece = furnitureService.findFurniturePiece(id);
              return ResponseEntity.ok(piece);
       }

        return ResponseEntity.notFound().build();
    }


    @PostMapping()
    public ResponseEntity<FurniturePieceDto> registerNewFurniturePiece(@RequestBody FurniturePieceDto piece) {
        furnitureService.addFurniturePiece(piece);

        return ResponseEntity.status(201).build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<FurniturePiece> deleteFurniturePiece(@PathVariable int id) {
        if (!furnitureService.existsById((long) id)) {
            return ResponseEntity.notFound().build();
        }
        furnitureService.deleteFurniturePiece((long) id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FurniturePieceDto> updateFurniturePiece(@PathVariable int id, @RequestBody FurniturePieceDto piece) {
        System.out.println(piece.toString());
        System.out.println("alaaa--------" + id);

        if (!furnitureService.existsById((long) id)) {
            return ResponseEntity.notFound().build();
        }
        furnitureService.updateFurniturePiece((long) id, piece);
        return ResponseEntity.ok().build();
    }

}








