package dan.turingbank.web.apiControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import dan.turingbank.model.dto.DepositCheckDto;
import dan.turingbank.model.dto.DepositRequestDto;
import dan.turingbank.model.dto.TransferRequestDto;
import dan.turingbank.service.interfaces.AccountService;
import dan.turingbank.service.interfaces.DepositService;
import dan.turingbank.service.interfaces.TransferService;
import dan.turingbank.util.Mapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/transaction")
@CrossOrigin(origins = "*")
public class TransactionController {

    @Autowired
    private TransferService transferService;

    @Autowired
    private DepositService depositService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private Mapper mapper;

    @PostMapping("/transfer")
    public ResponseEntity<?> executeTransfer(@RequestBody @Valid TransferRequestDto transferDto,
            Authentication authentication) throws Exception {

        String username = authentication.getPrincipal().toString();

        transferService.executeTransfer(mapper.fromTransactionDtoToTransaction(transferDto, username));

        return ResponseEntity.ok().build();
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody DepositRequestDto depositDto) throws Exception {

        System.out.println(depositDto.toString());

        depositService.executeDeposit(mapper.fromDepositDtoToDeposit(depositDto));

        return ResponseEntity.ok().build();
    }
     @GetMapping("/deposit/check")
    public ResponseEntity<?> depositCheck(@RequestParam("accountNumber") String accountNumber) throws Exception {


        var account = accountService.findByNumber(accountNumber);

        return ResponseEntity.ok().body(new DepositCheckDto(account.getClient().getName(), account.getNumber()));
    }

}
