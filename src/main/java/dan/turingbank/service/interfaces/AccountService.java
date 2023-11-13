package dan.turingbank.service.interfaces;


import dan.turingbank.model.entity.Account;

public interface AccountService {

    
    void deleteById(Long id) throws Exception;
    Account save(Account account);

    void edit(Account account) throws Exception;

    Account findById(Long id) throws Exception;

    Account findByNumber(String number) throws Exception;

    Account update(Account account);

    Account findByUsername(String username) throws Exception;

}
