package com.civa.futbolista.service;
import com.civa.futbolista.entities.DTO.FutbolistaDTO;
import com.civa.futbolista.entities.DTO.FutbolistaResposeDTO;

import org.springframework.data.domain.Page;



public interface FutbolistaService{
    Page<FutbolistaResposeDTO> obtenerListaFutbolistas(int page, int size);
    void registrarNuevoFutbolista(FutbolistaDTO futbolistaDTO);
    void eliminarFutbolista (Long id);
    FutbolistaResposeDTO actualizarFutbolista( Long id, FutbolistaDTO futbolistaDTO);
    FutbolistaResposeDTO buscarFutbolistaPorId(Long id);
}