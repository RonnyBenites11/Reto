package com.civa.futbolista.service.impl;

import com.civa.futbolista.entities.DTO.FutbolistaDTO;
import com.civa.futbolista.entities.DTO.FutbolistaResposeDTO;
import com.civa.futbolista.entities.Futbolista;
import com.civa.futbolista.entities.Posicion;
import com.civa.futbolista.repository.FutbolitaRepository;
import com.civa.futbolista.repository.PosicionRepository;
import com.civa.futbolista.service.FutbolistaService;
import com.civa.futbolista.utils.ErrorNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class FutbolistaServiceImpl implements FutbolistaService {
    private final FutbolitaRepository futbolitaRepository;
    private final PosicionRepository posicionRepository;

    @Override
    public Page<FutbolistaResposeDTO> obtenerListaFutbolistas(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return futbolitaRepository.findAll(pageable).map(this::convertToDto);
    }

    @Override
    public void registrarNuevoFutbolista(FutbolistaDTO futbolistaDTO) {
        Optional<Posicion> posicionOptional = posicionRepository.findById(futbolistaDTO.getPosicion());
        if (!posicionOptional.isPresent()) {
            throw new ErrorNotFoundException("Posicion no encontrado con ID: " + futbolistaDTO.getPosicion());
        }

        LocalDate fechaDeNacimiento = LocalDate.of(futbolistaDTO.getAnioNacimiento(), futbolistaDTO.getMesNacimiento(), futbolistaDTO.getDiaNacimiento());

        Futbolista futbolistaNuevo = Futbolista.builder()
                .posicion(posicionOptional.get())
                .nombres(futbolistaDTO.getNombres())
                .apellidos(futbolistaDTO.getApellidos())
                .fechaDeNacimiento(fechaDeNacimiento)
                .caracteristicas(futbolistaDTO.getCaracteristicas())
                .build();

        futbolitaRepository.save(futbolistaNuevo);
    }
    @Override
    public void eliminarFutbolista(Long id) {
        futbolitaRepository.deleteById(id);
    }

    @Override
    public FutbolistaResposeDTO actualizarFutbolista(Long id, FutbolistaDTO futbolistaDTO) {
        Optional<Futbolista> futbolistaOptional = futbolitaRepository.findById(id);
        if (!futbolistaOptional.isPresent()) {
            throw new ErrorNotFoundException("Futbolista no encontrado con ID: " + id);
        }

        Optional<Posicion> posicionOptional = posicionRepository.findById(futbolistaDTO.getPosicion());
        if (!posicionOptional.isPresent()) {
            throw new ErrorNotFoundException("Posicion no encontrado con ID: " + futbolistaDTO.getPosicion());
        }

        LocalDate fechaDeNacimiento = LocalDate.of(futbolistaDTO.getAnioNacimiento(), futbolistaDTO.getMesNacimiento(), futbolistaDTO.getDiaNacimiento());

        Futbolista futbolistaExistente = futbolistaOptional.get();
        futbolistaExistente.setNombres(futbolistaDTO.getNombres());
        futbolistaExistente.setApellidos(futbolistaDTO.getApellidos());
        futbolistaExistente.setFechaDeNacimiento(fechaDeNacimiento);
        futbolistaExistente.setCaracteristicas(futbolistaDTO.getCaracteristicas());
        futbolistaExistente.setPosicion(posicionOptional.get());

        futbolitaRepository.save(futbolistaExistente);

        return convertToDto(futbolistaExistente);
    }



    @Override
    public FutbolistaResposeDTO buscarFutbolistaPorId(Long id) {
        Optional<Futbolista> futbolistaOptional = futbolitaRepository.findById(id);
        return futbolistaOptional.map(this::convertToDto).orElse(null);
    }
    private FutbolistaResposeDTO convertToDto(Futbolista futbolista) {
        return FutbolistaResposeDTO.builder()
                .id(futbolista.getId())
                .nombres(futbolista.getNombres())
                .apellidos(futbolista.getApellidos())
                .fechaDeNacimiento(futbolista.getFechaDeNacimiento())
                .caracteristicas(futbolista.getCaracteristicas())
                .posicion(futbolista.getPosicion().getDescripcion())
                .build();
    }
}
