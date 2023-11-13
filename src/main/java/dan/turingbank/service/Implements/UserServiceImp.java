package dan.turingbank.service.Implements;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import dan.turingbank.model.entity.User;
import dan.turingbank.repository.UserRepository;
import dan.turingbank.service.interfaces.UserService;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) throws Exception {

        UserDetails users = loadUserByUsername(user.getUsername());

        if (users != null)
            throw new RuntimeException("Usuário já existe");

        return userRepository.saveAndFlush(user);
    }

    @Override
    public User findByAccountNumber(String accountNumber) throws Exception {

        return userRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Numero de conta inexistente"));

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserDetails> user = userRepository.findByUsername(username);

        return user.orElse(null);

    }

    @Override
    public boolean usernameAlreadyExists(String username){

        return null != loadUserByUsername(username);

    }


}
