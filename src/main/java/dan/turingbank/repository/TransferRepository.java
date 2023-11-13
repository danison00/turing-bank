package dan.turingbank.repository;

import dan.turingbank.model.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long>{
    
}
