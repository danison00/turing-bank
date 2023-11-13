package dan.turingbank.model.dto;

import java.math.BigDecimal;

public record DepositResponseDto(BigDecimal value, String dateAndHour) {
    
}
