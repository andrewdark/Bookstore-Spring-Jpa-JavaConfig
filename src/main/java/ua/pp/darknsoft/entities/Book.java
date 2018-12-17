package ua.pp.darknsoft.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book")
@NamedQueries({
        @NamedQuery(name = "Book.findAll",
                query = "select b from Book b"),

})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDateTime date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
