package ua.pp.darknsoft.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.pp.darknsoft.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> findById(Long id);

    List<Book> findAll();

    Page<Book> findAll(Pageable pageable);

    Book save(Book book);

    boolean isExist(Book book);
}
