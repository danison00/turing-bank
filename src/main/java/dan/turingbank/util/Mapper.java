package dan.turingbank.util;

import dan.turingbank.model.dto.AccountResponseDto;
import dan.turingbank.model.dto.CreateAccountRequest;
import dan.turingbank.model.dto.AccountDataResponseDto;
import dan.turingbank.model.dto.DepositRequestDto;
import dan.turingbank.model.dto.LoginDto;
import dan.turingbank.model.dto.TransferRequestDto;
import dan.turingbank.model.entity.Account;
import dan.turingbank.model.entity.Client;
import dan.turingbank.model.entity.Deposit;
import dan.turingbank.model.entity.Transfer;
import dan.turingbank.model.entity.User;

/**
 * Mapper
 */
public interface Mapper {

    Account fromCreateAccountRequestToAccount(CreateAccountRequest createAcc) throws Exception;
    AccountDataResponseDto fromAccountToAccountDataResponseDto(Account account) throws Exception;

    Client fromCreateAccountRequestToCliente(CreateAccountRequest createAcc) throws Exception;

    Transfer fromTransactionDtoToTransaction(TransferRequestDto transactionDto, String username) throws Exception;

    Deposit fromDepositDtoToDeposit(DepositRequestDto depositDto) throws Exception;

    AccountResponseDto fromAccountToAccountResponseDto(Account account);


}