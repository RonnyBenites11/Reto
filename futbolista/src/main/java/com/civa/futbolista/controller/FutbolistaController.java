package com.civa.futbolista.controller;

import com.civa.futbolista.entities.DTO.FutbolistaDTO;
import com.civa.futbolista.entities.DTO.FutbolistaResposeDTO;
import com.civa.futbolista.service.FutbolistaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/futbolista")
@CrossOrigin(origins = "http://localhost:4200")
@Validated
@RequiredArgsConstructor
public class FutbolistaController {
    private final FutbolistaService futbolistaService;

    @GetMapping
    public ResponseEntity<Page<FutbolistaResposeDTO>> obtenerListaFutbolista(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(futbolistaService.obtenerListaFutbolistas(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FutbolistaResposeDTO> buscarFutbolistaPorID(@PathVariable Long id) {
        FutbolistaResposeDTO futbolista = futbolistaService.buscarFutbolistaPorId(id);
        return futbolista != null ? ResponseEntity.ok(futbolista) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errorMessages = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> errorMessages.add(error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errorMessages);
    }

    @PostMapping
    public ResponseEntity<?> registrarNuevoFutbolista(@Valid @RequestBody FutbolistaDTO futbolistaDTO) {
        try {
            futbolistaService.registrarNuevoFutbolista(futbolistaDTO);
            return new ResponseEntity<>("Futbolista registrado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarFutbolista(@PathVariable Long id) {
        futbolistaService.eliminarFutbolista(id);
        return new ResponseEntity<>("Futbolista eliminado exitosamente", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FutbolistaResposeDTO> actualizarFutbolista(@PathVariable Long id, @Valid @RequestBody FutbolistaDTO futbolistaDTO) {
        FutbolistaResposeDTO futbolistaActualizado = futbolistaService.actualizarFutbolista(id, futbolistaDTO);
        return ResponseEntity.ok(futbolistaActualizado);
    }
}
