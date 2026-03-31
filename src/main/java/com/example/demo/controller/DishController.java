package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.demo.entity.Dish;
import com.example.demo.entity.Ingredient;
import com.example.demo.service.DishService;

@RestController
@RequestMapping("/dishes")
public class DishController {

    private final DishService service;

    public DishController(DishService service) {
        this.service = service;
    }

    @GetMapping
    public List<Dish> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}/ingredients")
    public ResponseEntity<?> updateIngredients(
            @PathVariable Long id,
            @RequestBody(required = false) List<Ingredient> ingredients
    ) {

        if (ingredients == null) {
            return ResponseEntity.badRequest()
                    .body("Request body is required");
        }

        try {
            service.updateIngredients(id, ingredients);
            return ResponseEntity.ok("Updated");

        } catch (Exception e) {
            return ResponseEntity.status(404)
                    .body("Dish.id=" + id + " is not found");
        }
    }

    @GetMapping("/{id}/ingredients")
    public ResponseEntity<?> getIngredientsByDish(
            @PathVariable Long id,
            @RequestParam(required = false) String ingredientName,
            @RequestParam(required = false) Double ingredientPriceAround
    ) {

        try {
            return ResponseEntity.ok(
                    service.getIngredientsByDish(id, ingredientName, ingredientPriceAround)
            );

        } catch (Exception e) {
            return ResponseEntity.status(404)
                    .body("Dish.id=" + id + " is not found");
        }
    }
}