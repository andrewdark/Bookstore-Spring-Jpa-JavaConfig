package ua.pp.darknsoft.repositories;

import org.springframework.stereotype.Repository;
import ua.pp.darknsoft.entities.Book;

@Repository
public class BookRepositoryImpl extends RepositoryImpl<Book, Long> implements BookRepository {

    public BookRepositoryImpl() {
        super(Book.class);
    }
}
