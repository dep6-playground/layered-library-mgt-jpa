package lk.ijse.dep.web.lms.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data @AllArgsConstructor @NoArgsConstructor
@Embeddable
public class BorrowalPK implements Serializable {

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "book_id")
    private String bookId;
}
