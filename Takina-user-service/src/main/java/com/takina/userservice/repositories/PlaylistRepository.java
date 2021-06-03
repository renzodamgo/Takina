package com.takina.userservice.repositories;

import com.takina.userservice.entities.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist,Long> {
    Optional<Playlist> findById(Long id);

    @Query("SELECT pl FROM Playlist pl")
    List<Playlist> findAll();
}
