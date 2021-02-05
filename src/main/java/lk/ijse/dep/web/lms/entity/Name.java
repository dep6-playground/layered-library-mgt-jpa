package lk.ijse.dep.web.lms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data @AllArgsConstructor @NoArgsConstructor
@Embeddable
public class Name {
    private String firstName;
    private String lastName;

}
