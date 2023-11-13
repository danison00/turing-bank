package dan.turingbank.service.interfaces;


import dan.turingbank.model.entity.Client;

public interface ClientService {

    public Client save(Client client);

    public void edit(Client client) throws Exception;

    public Client findById(Long id) throws Exception;

}