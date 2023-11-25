package dan.turingbank.web.apiControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dan.turingbank.model.dto.AccountResponseDto;
import dan.turingbank.model.dto.CreateAccountRequest;
import dan.turingbank.model.entity.Account;
import dan.turingbank.service.interfaces.AccountService;
import dan.turingbank.service.interfaces.CreateAccountService;
import dan.turingbank.util.Mapper;

@RestController
@RequestMapping("api/my-account")
@CrossOrigin(origins = "*")
public class MyAccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CreateAccountService createAccountService;

    @Autowired
    private Mapper mapper;

    @GetMapping
    public ResponseEntity<?> getDataAccount(Authentication authentication) throws Exception {

        String username = authentication.getPrincipal().toString();
        var a = accountService.findByUsername(username);
        AccountResponseDto accountDto = mapper.fromAccountToAccountResponseDto(a);
        System.out.println("data account send");

        a = accountService.findByUsername("joao");
        System.out.println(a.getFavorites());
        a = accountService.findByUsername("danison");
        System.out.println(a.getFavorites());

        return ResponseEntity.ok().body(accountDto);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(Authentication authentication) throws Exception {

        String username = authentication.getPrincipal().toString();
        createAccountService.deleteAccount(username);
        return ResponseEntity.ok().build();

    }

}
