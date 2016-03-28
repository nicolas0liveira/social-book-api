package com.aftermidnight.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aftermidnight.socialbooks.domain.Comentario;

public interface ComentariosRepository extends JpaRepository<Comentario, Long>{

}
