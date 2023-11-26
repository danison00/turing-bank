package dan.turingbank.service.Implements;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dan.turingbank.model.entity.Account;
import dan.turingbank.model.entity.FavoriteAccount;
import dan.turingbank.model.entity.Transfer;
import dan.turingbank.repository.FavoriteRepository;
import dan.turingbank.repository.TransferRepository;
import dan.turingbank.service.interfaces.AccountService;
import dan.turingbank.service.interfaces.TransferService;

@Service
public class TransferServiceImp extends AbstractTransactionalService implements TransferService {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private AccountService accountService;

    @Autowired 
    private FavoriteRepository favRep;

    @Transactional
    @Override
    public void executeTransfer(Transfer transfer) throws Exception {

        Account origin = transfer.getOrigin();
        Account destination = transfer.getDestination();

        if (origin.getNumber().equals(destination.getNumber())) {
            throw new RuntimeException("não é possível realizar uma tranferência para a mesma conta");
        }

        if (transfer.isSaveDestination()) {

            origin = addFav(origin, destination);
            
            accountService.update(origin);
        }

        if (!checkSufficientBalance(origin, transfer.getAmount())) {
            throw new RuntimeException("Saldo insuficiente!");
        }

        tranferAmount(transfer);

    }
    public Account addFav(Account origin, Account destination){

        Set<FavoriteAccount> favs = this.favRep.findFavoritesByAccountId(Long.valueOf(origin.getId()));
        
        boolean isFav = false;
        for (FavoriteAccount fav : favs) {
            if (fav.getNumber().equals(destination.getNumber())) {
               
               System.out.println("já é fav");
                return origin;
            }
        }

        System.out.println("adicionaou");
        origin.getFavorites().add(new FavoriteAccount(destination.getNumber(), destination.getClient().getName()));
        return origin;
    }

    @Transactional
    @Override
    public boolean checkSufficientBalance(Account account, BigDecimal value) throws Exception {

        if (account.getBalance().compareTo(value) < 0)
            return false;

        return true;

    }

    @Transactional
    @Override
    public void tranferAmount(Transfer transfer) {

        Account origin = transfer.getOrigin();
        Account destination = transfer.getDestination();

        incrementValue(destination, transfer.getAmount());
        decrementValue(origin, transfer.getAmount());

        transferRepository.saveAndFlush(transfer);
    }

    @Transactional
    @Override
    public void incrementBalance(Account account, BigDecimal value) {

        account.setBalance(account.getBalance().add(value));

    }

    @Transactional
    @Override
    public void decrementBalance(Account account, BigDecimal value) {

        account.setBalance(account.getBalance().subtract(value));

    }

}
