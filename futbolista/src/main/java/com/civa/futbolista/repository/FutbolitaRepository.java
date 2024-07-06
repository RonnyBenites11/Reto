package com.civa.futbolista.repository;

import com.civa.futbolista.entities.Futbolista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FutbolitaRepository  extends JpaRepository <Futbolista, Long> {
}
