package dan.turingbank.repository;

import java.util.Optional;

import dan.turingbank.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.security.core.userdetails.UserDetails;


public interface UserRepository extends JpaRepository<User, Long>{

    Optional<UserDetails> findByUsername(String username);

    @Query("SELECT a.client.user FROM Account a WHERE a.number = :accountNumber")
    Optional<User> findByAccountNumber(String accountNumber);
    
}
