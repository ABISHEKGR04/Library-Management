package com.example.demo;

public class Book {
    private Long id;
    private String name;
    private Long authorId;
    private String genre;

    public Book() {}

    public Book(Long id, String name, Long authorId, String genre) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
        this.genre = genre;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    // toString method for printing object

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authorId=" + authorId +
                ", genre='" + genre + '\'' +
                '}';
    }
}
