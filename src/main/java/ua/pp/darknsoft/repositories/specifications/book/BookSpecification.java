package ua.pp.darknsoft.repositories.specifications.book;

import org.springframework.data.jpa.domain.Specification;
import ua.pp.darknsoft.entities.Book;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class BookSpecification implements Specification<Book> {
    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        return criteriaBuilder.like(root.get("name"), "%book%");
    }
}
