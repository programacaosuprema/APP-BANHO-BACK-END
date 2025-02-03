package com.dip.danielaraujo.dip_project.dtos;

import com.dip.danielaraujo.dip_project.entities.DipEntity;
import com.dip.danielaraujo.dip_project.enums.AccessTypeEnum;
import com.dip.danielaraujo.dip_project.validation.dip.ValidAccess;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record DipDTO(UUID id,
                     @NotBlank(message = "O nome não pode estar em branco")
                     String name,
                     @NotBlank(message = "A descrição não pode estar em branco")
                     String description,
                     @NotBlank(message = "O estado não pode estar em branco")
                     @Pattern(regexp = "^[A-Z]{2}$", message = "O estado deve conter 2 letras maiúsculas")
                     @Pattern(regexp = "^(AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO)$", message = "Estado inválido")
                     String state,
                     @NotBlank(message = "A cidade não pode estar em branco")
                     @Size(max = 30, message = "O nome da cidade deve ter no máximo 30 caracteres")
                     String city,
                     @NotNull(message = "A temperatura não pode estar em branco")
                     BigDecimal temperature,
                     @NotBlank(message = "O acesso não pode estar em branco")
                     @ValidAccess
                     String access,
                     @NotBlank(message = "A localização não pode estar em branco")
                     String location,
                     @NotNull(message = "Deve haver pelo menos uma imagem")
                     @Size(min = 1, max = 5, message = "Deve haver pelo menos uma imagem")
                     List<ImageDTO> images) {
    public DipDTO(DipEntity dip){
        this(dip.getId(), dip.getName(), dip.getDescription(),
                dip.getState(), dip.getCity(), dip.getTemperature(), dip.getStringAccess(), dip.getLocation(), dip.getImages().stream().map(ImageDTO::new).toList());
    }

    public static DipDTO fromEntity(DipEntity dip) {
        return new DipDTO(dip);
    }
}
