package com.backend.repositories;

import com.backend.dtos.EventoDto;
import com.backend.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento,Long> {




}
