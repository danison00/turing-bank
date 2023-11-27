package dan.turingbank.web.apiControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dan.turingbank.model.dto.AccountDataResponseDto;
import dan.turingbank.model.dto.AccountResponseDto;
import dan.turingbank.model.dto.CreateAccountRequest;
import dan.turingbank.model.entity.Account;
import dan.turingbank.service.interfaces.AccountService;
import dan.turingbank.service.interfaces.CreateAccountService;
import dan.turingbank.util.Mapper;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/public/account")
public class CreateAccountController {

    @Autowired
    private CreateAccountService createAccountService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private Mapper mapper;

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountRequest createReq) throws Exception {

        System.out.println(createReq);
        Account account = createAccountService.createAccount(createReq);

        AccountDataResponseDto accountDataResponse = mapper.fromAccountToAccountDataResponseDto(account);

        return ResponseEntity.ok().build();

    }

}
