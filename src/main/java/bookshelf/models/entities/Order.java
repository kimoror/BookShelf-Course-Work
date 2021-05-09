package bookshelf.models.entities;

import bookshelf.models.enums.OrderStatus;
import bookshelf.models.enums.Role;
import bookshelf.models.enums.UserStatus;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;

@Component
@Entity
@Table( name = "orders")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    private Date orders_time;
    @Column(name = "status")
    private OrderStatus orderStatus;

    void preInsert(){
        if(this.orderStatus == null){
            this.orderStatus = OrderStatus.ACTIVE;
        }
    }
}
