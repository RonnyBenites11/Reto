package com.civa.futbolista.repository;

import com.civa.futbolista.entities.Posicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosicionRepository extends JpaRepository <Posicion, Long> {
}
