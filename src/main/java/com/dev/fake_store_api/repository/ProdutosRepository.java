package com.dev.fake_store_api.repository;

import com.dev.fake_store_api.entity.ProdutosEntity;
import com.dev.fake_store_api.entity.Rating;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@EnableAutoConfiguration
public class ProdutosRepository {
    private static  String SELECT_ALL = "SELECT * FROM produtos";
    private static  String SELECT_BY_CATEGORY = "SELECT * FROM produtos where category like ?";
    private static  String SELECT_BY_ID = "SELECT * FROM produtos where id = ?";
    private static final String INSERT = "INSERT INTO produtos (id, title, price, description, category, image, rate, count) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static  String DELETE = "DELETE FROM produtos WHERE id = ?";
    private static  String UPDATE = "UPDATE produtos SET title = ?, price = ?, description = ?, category = ?, image = ?, rate = ?, count = ? WHERE id = ?";

    private JdbcTemplate jdbcTemplate;

    public ProdutosRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ProdutosEntity insert (ProdutosEntity produtos) {
        Object[] produto = new Object[]{
                produtos.getId(),
                produtos.getTitle(),
                produtos.getPrice(),
                produtos.getDescription(),
                produtos.getCategory(),
                produtos.getImage(),
                produtos.getRating().getRate(),
                produtos.getRating().getCount()
        };
        jdbcTemplate.update(INSERT, produto);
        return produtos;
    }

    public List<ProdutosEntity> getAll() {
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<ProdutosEntity>(){
            @Override
            public ProdutosEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                ProdutosEntity produtos = new ProdutosEntity();
                produtos.setId(rs.getLong("id"));
                produtos.setTitle(rs.getString("title"));
                produtos.setPrice(rs.getDouble("price"));
                produtos.setDescription(rs.getString("description"));
                produtos.setCategory(rs.getString("category"));
                produtos.setImage(rs.getString("image"));
                double rating = rs.getDouble("rate");
                int ratingCount = rs.getInt("count");
                produtos.setRating(new Rating(rating, ratingCount));
                return produtos;
            }
        });
    }

    public List<ProdutosEntity> getByCategory(String category) {
        Object[] attr = new Object[]{category};
        return jdbcTemplate.query(SELECT_BY_CATEGORY, attr, new RowMapper<ProdutosEntity>(){
            @Override
            public ProdutosEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                ProdutosEntity produtos = new ProdutosEntity();
                produtos.setId(rs.getLong("id"));
                produtos.setTitle(rs.getString("title"));
                produtos.setPrice(rs.getDouble("price"));
                produtos.setDescription(rs.getString("description"));
                produtos.setCategory(rs.getString("category"));
                produtos.setImage(rs.getString("image"));
                double rating = rs.getDouble("rate");
                int ratingCount = rs.getInt("count");
                produtos.setRating(new Rating(rating, ratingCount));
                return produtos;
            }
        });
    }

    public ProdutosEntity getById(Long id) {
        Object[] attr = new Object[]{id};
        return jdbcTemplate.queryForObject(SELECT_BY_ID, attr, new RowMapper<ProdutosEntity>(){
            @Override
            public ProdutosEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                ProdutosEntity produtos = new ProdutosEntity();
                produtos.setId(rs.getLong("id"));
                produtos.setTitle(rs.getString("title"));
                produtos.setPrice(rs.getDouble("price"));
                produtos.setDescription(rs.getString("description"));
                produtos.setCategory(rs.getString("category"));
                produtos.setImage(rs.getString("image"));
                double rating = rs.getDouble("rate");
                int ratingCount = rs.getInt("count");
                produtos.setRating(new Rating(rating, ratingCount));
                return produtos;
            }
        });
    }

    public int remove (Long id) {
        return jdbcTemplate.update(DELETE, id);
    }

    public ProdutosEntity update (ProdutosEntity produtos) {
        Object[] produto = new Object[]{
                produtos.getId(),
                produtos.getTitle(),
                produtos.getPrice(),
                produtos.getDescription(),
                produtos.getCategory(),
                produtos.getImage(),
                produtos.getRating().getRate(),
                produtos.getRating().getCount()
        };
        jdbcTemplate.update(UPDATE, produto);
        return produtos;
    }

}
