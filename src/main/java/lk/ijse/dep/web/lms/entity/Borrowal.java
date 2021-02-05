package lk.ijse.dep.web.lms.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "member_book")
@Entity
public class Borrowal implements SuperEntity {

    @EmbeddedId
    private BorrowalPK borrowalPK;

    @Column(name = "borrowed_date",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date borrowedDate;

    @ManyToOne
    @Setter(AccessLevel.NONE)
    @JoinColumn(name = "member_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Member member;

    @ManyToOne
    @Setter(AccessLevel.NONE)
    @JoinColumn(name = "book_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Book book;

    public Borrowal(BorrowalPK borrowalPK, Date borrowedDate) {
        this.borrowalPK = borrowalPK;
        this.borrowedDate = borrowedDate;
    }

    public Borrowal(Integer memberId, Integer bookId, Date borrowedDate) {
        this.borrowalPK = new BorrowalPK(memberId,bookId);
        this.borrowedDate = borrowedDate;
    }
}
