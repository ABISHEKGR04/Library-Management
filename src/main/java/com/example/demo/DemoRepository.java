package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DemoRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DemoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAllBooks() {
        String sql = "SELECT * FROM book";
        return jdbcTemplate.query(sql, new BookRowMapper());
    }

    public void addBook(Book newBook) {
        String sql = "INSERT INTO book (name, author_id, genre) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, newBook.getName(), newBook.getAuthorId(), newBook.getGenre());
    }

    public Book getBookById(Long id) {
        String sql = "SELECT * FROM book WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BookRowMapper());
    }

    public void deleteBookById(Long id) {
        String sql = "DELETE FROM book WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Book(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getLong("author_id"),
                    rs.getString("genre")
            );
        }
    }
}
