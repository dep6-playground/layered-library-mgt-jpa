package lk.ijse.dep.web.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @AllArgsConstructor @NoArgsConstructor
public class ErrorDTO implements Serializable {
    private int status;
    private String error;
    private String message;

}
