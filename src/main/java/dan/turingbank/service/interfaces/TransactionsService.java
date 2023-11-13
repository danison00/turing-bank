package dan.turingbank.service.interfaces;

import dan.turingbank.model.entity.Account;

import java.math.BigDecimal;



public interface TransactionsService {
   
    boolean checkSufficientBalance(Account account, BigDecimal value) throws Exception;

    void incrementBalance(Account account, BigDecimal value);

    void decrementBalance(Account account, BigDecimal value);
}
