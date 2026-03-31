package com.example.demo.repository;
import com.example.demo.entity.Ingredient;
import java.util.List;
import java.util.Optional;

@Repository
public class IngredientRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Ingredient> findAll() {
        return jdbcTemplate.query("SELECT * FROM ingredient",
                (rs, rowNum) -> {
                    Ingredient i = new Ingredient();
                    i.setId(rs.getLong("id"));
                    i.setName(rs.getString("name"));
                    i.setCategory(rs.getString("category"));
                    i.setPrice(rs.getDouble("price"));
                    return i;
                });
    }
    public Optional<Ingredient> findById(Long id) {
        List<Ingredient> list = jdbcTemplate.query(
                "SELECT * FROM ingredient WHERE id=?",
                new Object[]{id},
                (rs, rowNum) -> {
                    Ingredient i = new Ingredient();
                    i.setId(rs.getLong("id"));
                    i.setName(rs.getString("name"));
                    i.setCategory(rs.getString("category"));
                    i.setPrice(rs.getDouble("price"));
                    return i;
                });

        return list.stream().findFirst();
    }
}
