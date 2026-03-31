package com.example.demo.controller;

import com.example.demo.entity.Dish;

import java.util.List;

@RestController
@RequestMapping("/dishes")
public class DishController {
    @Autowired
    private DishService service;

    // d) GET /dishes
    @GetMapping
    public List<Dish> getAll() {
        return service.getAll();
    }

    // e) PUT /dishes/{id}/ingredients
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
}
