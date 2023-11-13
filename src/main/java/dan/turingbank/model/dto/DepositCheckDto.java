package dan.turingbank.model.dto;

import java.time.LocalDate;

public record DepositCheckDto(
    String name,
    String accountNumber
) {
    
}
