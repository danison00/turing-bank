package dan.turingbank.service.Implements;

import java.math.BigDecimal;
import java.time.LocalDate;

import dan.turingbank.model.dto.CreateAccountRequest;
import dan.turingbank.model.entity.Account;
import dan.turingbank.service.interfaces.AccountService;
import dan.turingbank.service.interfaces.ClientService;
import dan.turingbank.service.interfaces.CreateAccountService;
import dan.turingbank.service.interfaces.UserService;
import dan.turingbank.util.MapperImp;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CreateAccountServiceImp implements CreateAccountService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;

    @Autowired
    private MapperImp mapper;

    @Transactional
    @Override
    public Account createAccount(CreateAccountRequest request) throws Exception {

        var account = mapper.fromCreateAccountRequestToAccount(request);
        var client = mapper.fromCreateAccountRequestToCliente(request);
        var user = client.getUser();

        user = userService.save(user);
        client.setUser(user);
        client = clientService.save(client);
        account.setNumber(generateAccountNumber());
        account.setOpeningDate(LocalDate.now());
        account.setClient(client);

        return accountService.save(account);

    }

    protected String generateAccountNumber() {

        return RandomStringUtils.randomNumeric(4);

    }

    @Override
    public void deleteAccount(String username) throws Exception {

        Account account = accountService.findByUsername(username);

        if (account.getBalance().compareTo(BigDecimal.valueOf(0)) > 0)
            throw new RuntimeException("Não foi possível excluir pois possui saldo");

        accountService.deleteById(account.getId());

    }
}
