package ua.pp.darknsoft.repositories.specifications.author;

import org.springframework.data.jpa.domain.Specification;
import ua.pp.darknsoft.entities.Author;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

public class FindAllAuthors implements Specification<Author> {

    @Override
    public Predicate toPredicate(Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.isNotEmpty(root.get("id"));
    }
}
