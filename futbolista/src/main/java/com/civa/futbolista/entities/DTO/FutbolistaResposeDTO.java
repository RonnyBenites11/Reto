package com.civa.futbolista.entities.DTO;
import lombok.*;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FutbolistaResposeDTO {
        private Long id;
        private String nombres;
        private String apellidos;
        private LocalDate fechaDeNacimiento;
        private String caracteristicas;
        private String posicion;

}
