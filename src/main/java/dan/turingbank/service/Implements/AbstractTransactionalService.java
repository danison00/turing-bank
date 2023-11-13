package dan.turingbank.service.Implements;

import java.math.BigDecimal;

import dan.turingbank.model.entity.Account;
import org.springframework.transaction.annotation.Transactional;


public abstract class AbstractTransactionalService {

    @Transactional
    public void incrementValue(Account account, BigDecimal value) {

        account.setBalance(account.getBalance().add(value));

    }

    @Transactional
    public void decrementValue(Account account, BigDecimal value) {

        account.setBalance(account.getBalance().subtract(value));

    }
}
