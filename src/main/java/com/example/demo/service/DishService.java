package com.example.demo.service;

import com.example.demo.entity.Dish;
import com.example.demo.entity.Ingredient;

import java.util.List;

@Service
public class DishService {
    @Autowired
    private DishRepository repository;

    public List<Dish> getAll() {
        return repository.findAll();
    }

    public void updateIngredients(Long id, List<Ingredient> ingredients) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Dish not found");
        }

        repository.updateIngredients(id, ingredients);
    }
}
