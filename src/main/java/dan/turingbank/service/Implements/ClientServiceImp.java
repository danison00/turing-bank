package dan.turingbank.service.Implements;

import dan.turingbank.model.entity.Client;
import dan.turingbank.repository.ClienteRepository;
import dan.turingbank.service.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClientServiceImp implements ClientService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Client save(Client client) {
        
        return clienteRepository.save(client);
    }


    @Override
    public void edit(Client client) throws Exception {

        findById(client.getId());
        clienteRepository.saveAndFlush(client);

    }

    @Override
    public Client findById(Long id) throws Exception {

        return clienteRepository.findById(id).orElseThrow(() -> new Exception("Cliente não encontrado!"));
    }

}
