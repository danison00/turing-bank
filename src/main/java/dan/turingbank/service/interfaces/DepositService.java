package dan.turingbank.service.interfaces;

import java.math.BigDecimal;

import dan.turingbank.model.entity.Account;
import dan.turingbank.model.entity.Deposit;

public interface DepositService {

    void executeDeposit(Deposit deposit) throws Exception;

    void incrementValue(Account account, BigDecimal value);

    
}
