package bookshelf.models.dto;

import bookshelf.models.entities.User;
import bookshelf.models.enums.Role;
import bookshelf.models.enums.UserStatus;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private long id;
    private String name;
    private int age;
    private String phone_number;
    private String address;
    private boolean have_account;
    private Role role;
    private UserStatus userStatus;
    private String email;
    private  String password;

    public UserDto(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.age = user.getAge();
        this.phone_number = user.getPhone_number();
        this.address = user.getAddress();
        this.role = user.getRole();
        this.userStatus = user.getUserStatus();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}