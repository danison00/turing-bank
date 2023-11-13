package dan.turingbank.repository;

import dan.turingbank.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Long>{

    Optional<Account> findByNumber(String number);
    
    @Query("SELECT a FROM Account a WHERE a.client.user.username = :username")
    Optional<Account> findByUsername(String username);

    void deleteById(Long id);
    
}
