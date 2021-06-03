package com.takina.userservice.repositories;

import com.takina.userservice.entities.Listado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ListadoRepository extends JpaRepository<Listado,Long> {
    Optional<Listado> findById(Long id);

    @Query("SELECT lis FROM Listado lis")
    List<Listado> findAll();
}
