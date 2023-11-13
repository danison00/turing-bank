package dan.turingbank.service.interfaces;


import dan.turingbank.model.entity.Transfer;

public interface TransferService extends TransactionsService{

    void executeTransfer(Transfer transaction) throws Exception;

    void tranferAmount(Transfer transaction);


}
