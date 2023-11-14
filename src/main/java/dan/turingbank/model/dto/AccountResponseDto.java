package dan.turingbank.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;

public record AccountResponseDto(
        String name,
        String cpf,
        String email,
        String telephone,
        String number,
        BigDecimal balance,
        String openingDate,
        ArrayList<DepositResponseDto> deposits,
        ArrayList<TransferResponseDto> tranfersReceived,
        ArrayList<TransferResponseDto> tranfersSend,
        ArrayList<AccountFavoriteDto> favoritesAccounts) {

}
