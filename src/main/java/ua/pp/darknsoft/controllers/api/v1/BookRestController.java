package ua.pp.darknsoft.controllers.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.pp.darknsoft.entities.Book;
import org.springframework.http.ResponseEntity;
import ua.pp.darknsoft.services.BookService;

import java.util.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
@RequestMapping("/v1")
public class BookRestController {
    @Autowired
    BookService bookService;

    @GetMapping(path = "/books/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Resource<Book>> getOneById(@PathVariable Long id) {
        Optional<Book> bookOptional = bookService.findById(id);
        if (!bookOptional.isPresent()) {
            System.out.println("not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Resource<Book> bookResource = new Resource<>(bookOptional.get());
        ControllerLinkBuilder allBooks = linkTo(methodOn(this.getClass()).getAll());
        ControllerLinkBuilder self = linkTo(methodOn(this.getClass()).getOneById(id));
        bookResource.add(self.withSelfRel());
        bookResource.add(allBooks.withRel("all-book"));

        return new ResponseEntity<>(bookResource, HttpStatus.OK);
    }

    @GetMapping(path = "/books", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Resources<Resource<Book>>> getAll() {
        List<Book> books = bookService.findAll();
        List<Resource<Book>> bookResources = new ArrayList<>();
        for (Book book : books) {
            Resource<Book> bookResource = new Resource<>(book);
            ControllerLinkBuilder allBooks = linkTo(methodOn(this.getClass()).getAll());
            ControllerLinkBuilder self = linkTo(methodOn(this.getClass()).getOneById(book.getId()));
            bookResource.add(self.withSelfRel());
            bookResource.add(allBooks.withRel("all-book"));
            bookResources.add(bookResource);
        }
        return new ResponseEntity<>(new Resources<>(bookResources,
                linkTo(methodOn(BookRestController.class).getAll()).withSelfRel()), HttpStatus.OK);
    }

    @PostMapping(path = "/books", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> create(@RequestBody Book book) {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "/books", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {

        return new ResponseEntity<>(book, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/books/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void delete(@PathVariable Long id) {
        System.out.println("delete");
    }

    private List<Link> getSingleItemLinks(long id) {

//        return Arrays.asList(linkTo(methodOn(BookRestController.class).getOneById(id)).withSelfRel()
//                        .andAffordance(afford(methodOn(BookRestController.class).update(id, null)))
//                        .andAffordance(afford(methodOn(BookRestController.class).delete(id))),
//                linkTo(methodOn(BookRestController.class).getAll()).withRel("employees"));
        return Collections.emptyList();
    }
}
