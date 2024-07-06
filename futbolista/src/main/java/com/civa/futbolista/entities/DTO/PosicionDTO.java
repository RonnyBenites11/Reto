package com.civa.futbolista.entities.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PosicionDTO {
    private Long id;
    @Size(min = 5, max = 20,  message = "La descripcion debe tener entre 5 y 20 caracteres")
    private String descripcion;

}
