package com.civa.futbolista.service.impl;

import com.civa.futbolista.entities.DTO.PosicionDTO;
import com.civa.futbolista.entities.Posicion;
import com.civa.futbolista.repository.PosicionRepository;
import com.civa.futbolista.service.PosicionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PosicionServiceImpl implements PosicionService {
    private final PosicionRepository posicionRepository;
    @Override
    public List<Posicion> obtenerListaPosiciones() {
        return posicionRepository.findAll();
    }

    @Override
    public void registrarNuevoPosicion(PosicionDTO posicionDTO) {
        Posicion posicionNuevo = Posicion.builder()
                .descripcion(posicionDTO.getDescripcion())
                .build();
        posicionRepository.save(posicionNuevo);
    }

    @Override
    public void eliminarPosicion(Long id) {
        posicionRepository.deleteById(id);
    }

    @Override
    public Posicion actualizarPosicion(Posicion posicion) {
        Posicion posicionUpdated = posicionRepository.save(posicion);
        return posicionUpdated;
    }

    @Override
    public Optional<Posicion> buscarPosicionPorId(Long id) {
            return posicionRepository.findById(id);
    }
}
