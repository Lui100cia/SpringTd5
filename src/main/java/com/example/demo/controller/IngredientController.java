package com.example.demo.controller;
@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    @Autowired
    private IngredientService service;

    // a) GET /ingredients
    @GetMapping
    public List<Ingredient> getAll() {
        return service.getAll();
    }

    // b) GET /ingredients/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getById(id));
        } catch (Exception e) {
            return ResponseEntity.status(404)
                    .body("Ingredient.id=" + id + " is not found");
        }
    }

    // c) GET /ingredients/{id}/stock
    @GetMapping("/{id}/stock")
    public ResponseEntity<?> getStock(
            @PathVariable Long id,
            @RequestParam(required = false) String at,
            @RequestParam(required = false) String unit
    ) {

        if (at == null || unit == null) {
            return ResponseEntity.badRequest()
                    .body("Either mandatory query parameter `at` or `unit` is not provided.");
        }

        try {
            Ingredient ingredient = service.getById(id);

            Map<String, Object> result = new HashMap<>();
            result.put("unit", unit);
            result.put("value", 100); // simulation stock

            return ResponseEntity.ok(result);

        } catch (Exception e) {
            return ResponseEntity.status(404)
                    .body("Ingredient.id=" + id + " is not found");
        }
    }
}
