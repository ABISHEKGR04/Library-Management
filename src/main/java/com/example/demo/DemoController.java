package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerInterceptor;


import java.util.List;

@Controller
//@RequestMapping("books")
public class DemoController {

    private final DemoService demoService;

    @Autowired
    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @Controller
    public static class AuthInterceptor implements HandlerInterceptor {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
            if (username == null) {
                response.sendRedirect("/?error=login");
                return false;
            }
            return true;
        }
    }

    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, HttpSession session) {
        // Save the username in session
        session.setAttribute("username", username);
        // Redirect user to /books endpoint
        return "redirect:/books";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Save the username in session
        session.removeAttribute("username");
        // Redirect user to /books endpoint
        return "redirect:/books";
    }

    @GetMapping("/books")
    public String getAll(Model model) {
        List<Book> books = demoService.getAllBooks();
        model.addAttribute("books", books);
        return "listbooks";
    }

    @PostMapping("/books")
    public String addBook(@ModelAttribute("newBook") Book newBook) {
        demoService.addBook(newBook);
        return "redirect:/books";
    }

    @GetMapping("/books/{id}")
    public String getBookDetails(@PathVariable("id") Long id, Model model) {
        Book book = demoService.getBookById(id);
        model.addAttribute("book", book);
        return "bookdetails";
    }

    @DeleteMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        demoService.deleteBookById(id);
        return "redirect:/books";
    }
}
