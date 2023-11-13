package dan.turingbank.repository;

import dan.turingbank.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Client, Long> {
    
}
