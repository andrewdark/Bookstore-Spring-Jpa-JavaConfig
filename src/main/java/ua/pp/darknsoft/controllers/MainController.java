package ua.pp.darknsoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.pp.darknsoft.entities.Book;
import ua.pp.darknsoft.services.BookService;
import ua.pp.darknsoft.services.BookServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    BookService bb;

    @GetMapping(path = "/")
    public String index(Model dasModel) {

        List<Book> books = bb.findAll();
        dasModel.addAttribute("curDate",LocalDateTime.now());
        dasModel.addAttribute("books", books);
        return "index";
    }
}
