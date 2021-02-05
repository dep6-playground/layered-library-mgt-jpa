package lk.ijse.dep.web.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @AllArgsConstructor @NoArgsConstructor
public class BookDTO implements Serializable {
    private String id;
    private String name;
    private String category;
    private String author;
}
