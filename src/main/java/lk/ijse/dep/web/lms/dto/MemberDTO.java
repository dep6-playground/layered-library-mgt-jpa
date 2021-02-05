package lk.ijse.dep.web.lms.dto;

import lk.ijse.dep.web.lms.entity.Gender;
import lk.ijse.dep.web.lms.entity.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @AllArgsConstructor @NoArgsConstructor
public class MemberDTO implements Serializable {
    private String id;
    private Name name;
    private String contact;
    private Gender gender;
}
