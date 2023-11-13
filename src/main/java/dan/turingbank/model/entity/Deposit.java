package dan.turingbank.model.entity;
import java.math.BigDecimal;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "Deposits")
public class Deposit extends TransactionAbstract {


    public Deposit(Account destination, BigDecimal value) {
        super(destination, value);      
     
    }

}
