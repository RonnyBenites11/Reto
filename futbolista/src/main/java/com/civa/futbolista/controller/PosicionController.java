package com.civa.futbolista.controller;
import com.civa.futbolista.entities.DTO.PosicionDTO;
import com.civa.futbolista.entities.Posicion;
import com.civa.futbolista.service.PosicionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posicion")
@Validated
@RequiredArgsConstructor
public class PosicionController {
    private final PosicionService posicionService;
    @GetMapping
    public ResponseEntity<List<Posicion>> obtenerListaPosiciones() {
        return ResponseEntity.ok(posicionService.obtenerListaPosiciones());
    }
    @PostMapping
    public ResponseEntity<?> registrarNuevaPosicion(@Valid @RequestBody PosicionDTO posicionDTO) {
        try {
            posicionService.registrarNuevoPosicion(posicionDTO);
            return new ResponseEntity<>("Posicion registrado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPosicion(@PathVariable Long id) {
        posicionService.eliminarPosicion(id);
        return new ResponseEntity<>("Posicion eliminado exitosamente", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Posicion> actualizarPosicion(@PathVariable Long id, @Valid @RequestBody PosicionDTO posicionDTO) {
        Optional<Posicion> posicionOptional = posicionService.buscarPosicionPorId(id);
        if (!posicionOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Posicion posicionExistente = posicionOptional.get();
        posicionExistente.setDescripcion(posicionDTO.getDescripcion());


        Posicion posicionActualizado = posicionService.actualizarPosicion(posicionExistente);
        return ResponseEntity.ok(posicionActualizado);
    }

}
