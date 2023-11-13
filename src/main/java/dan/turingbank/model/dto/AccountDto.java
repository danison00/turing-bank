package dan.turingbank.model.dto;
import dan.turingbank.model.entity.Deposit;
import dan.turingbank.model.entity.Transfer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;



public record AccountDto(
        String number,
        BigDecimal balance,
        LocalDate openingDate,
        List<Deposit> deposits,
        List<Transfer> tranferReceived,
        List<Transfer> tranferSend) {

}
