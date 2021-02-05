package lk.ijse.dep.web.lms.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@ToString(exclude = "book2Member")
@Entity
@Table(name = "book")
@AllArgsConstructor @NoArgsConstructor @Data
public class Book implements SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;

    private String name;

    private String category;

    private String author;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "book")
    private List<Borrowal> book2Member;

    public Book(String id, String name, String category, String author) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.author = author;
    }
}
