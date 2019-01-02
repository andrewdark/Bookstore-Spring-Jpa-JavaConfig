package ua.pp.darknsoft.repositories;

import org.springframework.stereotype.Repository;
import ua.pp.darknsoft.entities.Author;

@Repository
public class AuthorRepositoryImpl extends RepositoryImpl<Author, Long> implements AuthorRepository {

    public AuthorRepositoryImpl() {
        super(Author.class);
    }
}
