package dan.turingbank.service.interfaces;


import dan.turingbank.model.dto.CreateAccountRequest;
import dan.turingbank.model.entity.Account;

public interface CreateAccountService {

    public Account createAccount(CreateAccountRequest request) throws Exception;

    public void deleteAccount(String username) throws Exception;

}
