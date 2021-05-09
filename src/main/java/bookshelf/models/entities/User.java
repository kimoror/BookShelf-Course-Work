package bookshelf.models.entities;

import bookshelf.models.enums.Role;
import bookshelf.models.enums.UserStatus;
import lombok.*;

import javax.persistence.*;

/**
 * User entity
 */
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int age;
    private String phone_number;
    private String address;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private UserStatus userStatus;
    private String email;
    private  String password;

    @PrePersist
    void preInsert(){
        if(this.role == null){
            this.role = Role.USER;
        }
        if(this.userStatus == null){
            this.userStatus = UserStatus.ACTIVE;
        }
    }
}
