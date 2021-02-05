package lk.ijse.dep.web.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;


@Data @AllArgsConstructor @NoArgsConstructor
public class BorrowalDTO implements Serializable {

    private String memberId;
    private String bookId;
    private Date borrowedDate;
}
