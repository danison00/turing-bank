package dan.turingbank.web.apiControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dan.turingbank.model.dto.DepositCheckDto;
import dan.turingbank.model.dto.DepositRequestDto;
import dan.turingbank.service.interfaces.AccountService;
import dan.turingbank.service.interfaces.DepositService;
import dan.turingbank.util.Mapper;

@RestController
@RequestMapping("api/public/transaction/deposit")
public class DepositController {

    @Autowired
    private DepositService depositService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private Mapper mapper;

    @PostMapping()
    public ResponseEntity<?> deposit(@RequestBody DepositRequestDto depositDto) throws Exception {

        System.out.println(depositDto.toString());

        depositService.executeDeposit(mapper.fromDepositDtoToDeposit(depositDto));

        return ResponseEntity.ok().build();
    }

    @GetMapping("/check")
    public ResponseEntity<?> depositCheck(@RequestParam("accountNumber") String accountNumber) throws Exception {

        System.out.println("hgtftyf");
        var account = accountService.findByNumber(accountNumber);
        return ResponseEntity.ok().body(new DepositCheckDto(account.getClient().getName(), account.getNumber()));
    }
}
