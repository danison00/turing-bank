package dan.turingbank.model.dto;

import java.math.BigDecimal;

public record DepositRequestDto(String accountNumber, BigDecimal value) {
    
}
