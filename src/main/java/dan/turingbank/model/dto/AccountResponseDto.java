package dan.turingbank.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;

public record AccountResponseDto(
        String name,
        String number,
        BigDecimal balance,
        String openingDate,
        ArrayList<DepositResponseDto> deposits,
        ArrayList<TransferResponseDto> tranfersReceived,
        ArrayList<TransferResponseDto> tranfersSend,
        ArrayList<AccountFavoriteDto> favoritesAccounts) {

}
