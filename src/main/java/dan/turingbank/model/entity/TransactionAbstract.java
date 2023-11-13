package dan.turingbank.model.entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class TransactionAbstract implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="destination_id_fk")
    private Account destination;

    private BigDecimal amount;
    private String dateAndHour;
    
    public TransactionAbstract(Account destination, BigDecimal value) {
        this.destination = destination;
        this.amount = value;
        this.dateAndHour = LocalDateTime.now().toString();
    }
    
}
