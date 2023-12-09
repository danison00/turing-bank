package dan.turingbank.model.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@NoArgsConstructor
@Table(name="Tranfer")
public class Transfer extends TransactionAbstract{
    
    @ManyToOne
    @JoinColumn(name="origin_id_fk")
    private Account origin;

    @Transient
    private boolean saveDestination;
    
    @JsonIgnore
    public Transfer(Account origin, Account destination, BigDecimal value, boolean saveDestination) {
        
        super(destination, value);
        this.origin = origin;
        this.saveDestination = saveDestination;      
    }

    
    
    

}
