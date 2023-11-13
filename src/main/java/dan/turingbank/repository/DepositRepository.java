package dan.turingbank.repository;

import dan.turingbank.model.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
    
}
