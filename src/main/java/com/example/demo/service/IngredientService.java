package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.List;

import com.example.demo.entity.Ingredient;
import com.example.demo.repository.IngredientRepository;

@Service
public class IngredientService {

    private final IngredientRepository repository;

    public IngredientService(IngredientRepository repository) {
        this.repository = repository;
    }

    public List<Ingredient> getAll() {
        return repository.findAll();
    }

    public Ingredient getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Ingredient.id=" + id + " is not found"));
    }
}