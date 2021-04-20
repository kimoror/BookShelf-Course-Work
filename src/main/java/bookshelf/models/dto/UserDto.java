package bookshelf.models.dto;

import bookshelf.models.entities.User;
import lombok.*;

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

    public UserDto(User user){
        this.name = user.getName();
        this.age = user.getAge();
        this.phone_number = user.getPhone_number();
        this.address = user.getAddress();
        this.have_account = user.isHave_account();
    }

}