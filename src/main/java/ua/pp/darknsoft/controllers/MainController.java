package ua.pp.darknsoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.pp.darknsoft.entities.Book;
import ua.pp.darknsoft.services.BookServiceImpl;

import java.util.List;


@Controller
public class MainController {
    @Autowired
    BookServiceImpl bb;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String index(Model dasModel) {
        List<Book> books = bb.findAll();
        dasModel.addAttribute("books", books);
        return "index";
    }
}
