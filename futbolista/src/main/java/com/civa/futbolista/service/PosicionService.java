package com.civa.futbolista.service;
import com.civa.futbolista.entities.DTO.PosicionDTO;
import com.civa.futbolista.entities.Posicion;
import java.util.List;
import java.util.Optional;

public interface PosicionService {
    List<Posicion> obtenerListaPosiciones();
    void registrarNuevoPosicion(PosicionDTO posicionDTO);
    void eliminarPosicion (Long id);
    Posicion actualizarPosicion(Posicion posicion);
    Optional<Posicion> buscarPosicionPorId(Long id);

}
