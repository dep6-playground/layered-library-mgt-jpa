package lk.ijse.dep.web.lms.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@ToString(exclude = "member2Book")
@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "member")
public class Member implements SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Embedded
    private Name name;

    private String contact;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "member")
    private List<Borrowal> member2Book;

    public Member(Integer id, Gender gender, Name name, String contact) {
        this.id = id;
        this.gender = gender;
        this.name = name;
        this.contact = contact;
    }
}
