package dan.turingbank.service.Implements;

import java.math.BigDecimal;

import dan.turingbank.model.entity.Account;
import dan.turingbank.model.entity.Deposit;
import dan.turingbank.repository.DepositRepository;
import dan.turingbank.service.interfaces.AccountService;
import dan.turingbank.service.interfaces.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dan.turingbank.model.entity.Account;
import dan.turingbank.model.entity.Deposit;
import dan.turingbank.repository.DepositRepository;
import dan.turingbank.service.interfaces.AccountService;
import dan.turingbank.service.interfaces.DepositService;

@Service
public class DepositServiceImp implements DepositService {

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private AccountService accountService;

    @Transactional
    @Override
    public void executeDeposit(Deposit deposit) throws Exception {

        Account destination = deposit.getDestination();
        BigDecimal value = deposit.getAmount();
       
        if (value.compareTo(BigDecimal.ZERO) <= 0)
            throw new RuntimeException("Verifique o valor do depósito");

        incrementValue(destination, value);
        accountService.update(destination);

        depositRepository.save(deposit);

    }

    @Transactional
    @Override
    public void incrementValue(Account account, BigDecimal value) {

        account.setBalance(account.getBalance().add(value));

    }

}
