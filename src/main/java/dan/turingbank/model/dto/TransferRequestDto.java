package dan.turingbank.model.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TransferRequestDto(
    
    @NotBlank @NotNull
    String accountDestination,
    
    @NotNull
    BigDecimal value,
    
    @NotNull
    boolean saveDestination) {

}
