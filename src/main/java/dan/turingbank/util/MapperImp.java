package dan.turingbank.util;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import dan.turingbank.model.dto.AccountFavoriteDto;
import dan.turingbank.model.dto.AccountResponseDto;
import dan.turingbank.model.dto.CreateAccountRequest;
import dan.turingbank.model.dto.AccountDataResponseDto;
import dan.turingbank.model.dto.DepositRequestDto;
import dan.turingbank.model.dto.DepositResponseDto;
import dan.turingbank.model.dto.LoginDto;
import dan.turingbank.model.dto.TransferRequestDto;
import dan.turingbank.model.dto.TransferResponseDto;
import dan.turingbank.model.entity.Account;
import dan.turingbank.model.entity.Client;
import dan.turingbank.model.entity.Deposit;
import dan.turingbank.model.entity.Roles;
import dan.turingbank.model.entity.Transfer;
import dan.turingbank.model.entity.User;
import dan.turingbank.service.interfaces.AccountService;
import dan.turingbank.service.interfaces.UserService;

@Component
public class MapperImp implements Mapper {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Override
    public Account fromCreateAccountRequestToAccount(CreateAccountRequest createAcc) throws Exception {

        return new Account(createAcc.typeAccount());

    }

    @Override
    public Client fromCreateAccountRequestToCliente(CreateAccountRequest createAcc) throws Exception {

        String password = new BCryptPasswordEncoder().encode(createAcc.login().password());

        User user = new User(createAcc.login().username(), password, Roles.USER);

        return new Client(
                createAcc.cpf(),
                createAcc.name(),
                createAcc.email(),
                createAcc.telephone(),
                user);

    }

    @Override
    public Transfer fromTransactionDtoToTransaction(TransferRequestDto transactionDto, String username)
            throws Exception {

        Account accountOrigin = accountService.findByUsername(username);
        Account accountDestination = accountService.findByNumber(transactionDto.accountDestination());
        BigDecimal value = transactionDto.value();

        return new Transfer(accountOrigin, accountDestination, value, transactionDto.saveDestination());

    }

    public Deposit fromDepositDtoToDeposit(DepositRequestDto depositDto) throws Exception {

        if (depositDto.value() == null)
            throw new RuntimeException("Verifique o valor do depósito");

        Account accountOrigin = accountService.findByNumber(depositDto.accountNumber());

        return new Deposit(accountOrigin, depositDto.value());

    }

    @Override
    public AccountResponseDto fromAccountToAccountResponseDto(Account account) {

        var deposits = new ArrayList<DepositResponseDto>();
        var transfersReceived = new ArrayList<TransferResponseDto>();
        var transfersSend = new ArrayList<TransferResponseDto>();
        var favoritesAccounts = new ArrayList<AccountFavoriteDto>();

        account.getDeposits().stream().forEach(
                deposit -> deposits.add(fromDepositToDepositResponseDto(deposit)));

        account.getTransferReceived().stream().forEach(
                transfer -> transfersReceived.add(fromTransferToTransferReceivedResponseDto(transfer)));

        account.getTransferSend().stream().forEach(
                transfer -> transfersSend.add(fromTransferToTransferSendResponseDto(transfer)));
        account.getFavorites().stream().forEach(
                favorite -> favoritesAccounts
                        .add(new AccountFavoriteDto(favorite.getClient().getName(), favorite.getNumber())));

        return new AccountResponseDto(
                account.getClient().getName(),
                account.getClient().getCpf(),
                account.getClient().getEmail(),
                account.getClient().getTelephone(),
                account.getNumber(),
                account.getBalance(),
                account.getOpeningDate().toString(),
                deposits,
                transfersReceived,
                transfersSend,
                favoritesAccounts);

    }

    public DepositResponseDto fromDepositToDepositResponseDto(Deposit deposit) {

        return new DepositResponseDto(deposit.getAmount(), deposit.getDateAndHour());

    }

    public TransferResponseDto fromTransferToTransferReceivedResponseDto(Transfer transfer) {

        return new TransferResponseDto(
                transfer.getOrigin().getClient().getName(),
                transfer.getOrigin().getNumber(),
                transfer.getAmount(),
                transfer.getDateAndHour());

    }

    public TransferResponseDto fromTransferToTransferSendResponseDto(Transfer transfer) {

        return new TransferResponseDto(
                transfer.getDestination().getClient().getName(),
                transfer.getDestination().getNumber(),
                transfer.getAmount(),
                transfer.getDateAndHour());

    }

    @Override
    public AccountDataResponseDto fromAccountToAccountDataResponseDto(Account account) throws Exception {
        return new AccountDataResponseDto(account.getClient().getCpf(), account.getClient().getName(),
                account.getClient().getTelephone(), account.getClient().getEmail(),
                account.getNumber(), account.getOpeningDate(), account.getType().name());
    }

}
