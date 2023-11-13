package dan.turingbank.model.dto;


import dan.turingbank.model.entity.TypeAccount;

public record CreateAccountRequest(
        String cpf,
        String name,
        String telephone,
        String email,
        TypeAccount typeAccount,
        LoginDto login) {
}
