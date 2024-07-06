package com.civa.futbolista.entities.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class FutbolistaDTO {

    private Long id;
    @NotNull(message = "El nombre es un campo obligatorio")
    @Size (min = 5, max = 10, message = "Maximo 10 caracteres")
    private String nombres;
    @NotNull(message = "Los apellidos son obligatorios")
    @Size (min = 5, max = 20, message = "Maximo 20 caracteres")
    private String apellidos;

    @NotNull(message = "El día de nacimiento no debe ser nulo")
    @Min(value = 1, message = "El día de nacimiento debe ser entre 1 y 31")
    @Max(value = 31, message = "El día de nacimiento debe ser entre 1 y 31")
    private Integer diaNacimiento;

    @NotNull(message = "El mes de nacimiento no debe ser nulo")
    @Min(value = 1, message = "El mes de nacimiento debe ser entre 1 y 12")
    @Max(value = 12, message = "El mes de nacimiento debe ser entre 1 y 12")
    private Integer mesNacimiento;

    @NotNull(message = "El año de préstamo no debe ser nulo")
    @Min(value = 1900, message = "El año de nacimiento debe ser razonable")
    private Integer anioNacimiento;
    @Size (min = 5, max = 40, message = "Maximo 20 caracteres")
    private String caracteristicas;
    @NotNull(message = "ID de posicion es obligatorio")
    private Long posicion;



}
