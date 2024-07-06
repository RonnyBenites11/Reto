package com.civa.futbolista.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Posicion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    @JsonIgnore
    @OneToMany(mappedBy = "posicion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Futbolista> futbolista;
}
