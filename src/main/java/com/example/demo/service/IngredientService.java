package com.example.demo.service;

import com.example.demo.entity.Ingredient;
import com.example.demo.repository.IngredientRepository;

import java.util.List;
@Service
public class IngredientService {
    @Autowired
    private IngredientRepository repository;

    public List<Ingredient> getAll() {
        return repository.findAll();
    }

    public Ingredient getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Ingredient.id=" + id + " is not found"));
    }
}
