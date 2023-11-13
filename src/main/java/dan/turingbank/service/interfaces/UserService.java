package dan.turingbank.service.interfaces;

import dan.turingbank.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService  extends UserDetailsService{

    User save(User user) throws Exception;

    User findByAccountNumber(String accountNumber) throws Exception;

    boolean usernameAlreadyExists(String username);


}
