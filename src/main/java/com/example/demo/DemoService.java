package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoService {
    private final DemoRepository demoRepository;

    @Autowired
    public DemoService(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    public List<Book> getAllBooks() {
        return demoRepository.getAllBooks();
    }

    public void addBook(Book newBook) {
        demoRepository.addBook(newBook);
    }

    public Book getBookById(Long id) {
        // validation
        return demoRepository.getBookById(id);
    }

    public void deleteBookById(Long id) {
        // You can add validation logic here if needed
        demoRepository.deleteBookById(id);
    }
}
