package dan.turingbank.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dan.turingbank.model.entity.FavoriteAccount;
import jakarta.transaction.Transactional;

public interface FavoriteRepository extends JpaRepository<FavoriteAccount, Long>{


    
    @Query("SELECT a.favorites FROM Account a WHERE a.id = :accountId")
    Set<FavoriteAccount> findFavoritesByAccountId(@Param("accountId") Long accountId);
    
}
