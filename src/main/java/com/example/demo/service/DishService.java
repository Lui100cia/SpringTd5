package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.List;

import com.example.demo.entity.Dish;
import com.example.demo.entity.Ingredient;
import com.example.demo.repository.DishRepository;

@Service
public class DishService {

    private final DishRepository repository;

    public DishService(DishRepository repository) {
        this.repository = repository;
    }

    public List<Dish> getAll() {
        return repository.findAll();
    }

    public Dish getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Dish.id=" + id + " is not found"));
    }

    public void updateIngredients(Long id, List<Ingredient> ingredients) {
        Dish dish = getById(id);

        // On garde uniquement les ingrédients existants
        dish.setIngredients(ingredients);

        repository.save(dish);
    }

    // 🔥 DERNIER TD (filtrage)
    public List<Ingredient> getIngredientsByDish(
            Long id,
            String ingredientName,
            Double ingredientPriceAround
    ) {
        Dish dish = getById(id);

        List<Ingredient> ingredients = dish.getIngredients();

        if (ingredientName != null) {
            ingredients = ingredients.stream()
                    .filter(i -> i.getName().toLowerCase()
                            .contains(ingredientName.toLowerCase()))
                    .toList();
        }

        if (ingredientPriceAround != null) {
            double min = ingredientPriceAround - 50;
            double max = ingredientPriceAround + 50;

            ingredients = ingredients.stream()
                    .filter(i -> i.getPrice() >= min && i.getPrice() <= max)
                    .toList();
        }

        return ingredients;
    }
}