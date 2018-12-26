package ua.pp.darknsoft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.darknsoft.entities.Book;
import ua.pp.darknsoft.repositories.BookRepository;
import ua.pp.darknsoft.repositories.BookRepositoryImpl;
import ua.pp.darknsoft.repositories.specifications.book.BookSpecification;
import ua.pp.darknsoft.repositories.specifications.book.FindByNameAndDateSpecification;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Book> findAll() {

        List<Book> contacts = bookRepository.query(new BookSpecification());
        //new ArrayList<>();//emf.createNamedQuery("Book.findAll", Book.class).getResultList();
        return contacts;
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public boolean isExist(Book book) {

        List<Book> books = bookRepository.query(new FindByNameAndDateSpecification(book));
        if (books.isEmpty()) return false;
        return true;
    }
}
