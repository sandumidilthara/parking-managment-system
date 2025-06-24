package lk.ijse.userservice.dto;


import lk.ijse.userservice.entity.UserRole;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class UserDTO {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private UserRole role;
    private Date createdAt;
}
