package com.dip.danielaraujo.dip_project.dtos;

import com.dip.danielaraujo.dip_project.entities.ClientEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.UUID;

public record ClientDTO(
        UUID id,

        @NotBlank(message = "O nome não pode estar em branco")
        @jakarta.validation.constraints.Pattern(regexp = "^[\\p{L} ]+$", message = "O nome deve conter apenas letras e espaços")
        String firstName,

        @Pattern(regexp = "^[\\p{L} ]+$", message = "O sobrenome deve conter apenas letras e espaços")
        String lastName,

        @NotBlank(message = "O email é obrigatório")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Email inválido")
        String email,

        @NotBlank(message = "O telefone é obrigatório")
        @Pattern(regexp = "^[0-9]{11}$", message = "O telefone deve conter 11 dígitos numéricos")
        String phoneNumber,

        @Valid
        ImageDTO image,

        @NotBlank(message = "A senha não pode estar em branco")
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+=-]).{8,12}$",
                message = "A senha deve ter entre 8 e 12 caracteres, incluir pelo menos uma letra maiúscula, um número e um caractere especial"
        )
        @Size(min = 8, max = 12, message = "A senha deve ter entre 8 e 12 caracteres")

        String password
) {
    public ClientDTO(ClientEntity client) {
        this(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getPhoneNumber(),
                client.getImage() != null ? new ImageDTO(client.getImage()) : null,
                null // Para não expor a senha na conversão
        );
    }

    public static ClientDTO fromEntity(ClientEntity client) {
        return new ClientDTO(client);
    }
}
