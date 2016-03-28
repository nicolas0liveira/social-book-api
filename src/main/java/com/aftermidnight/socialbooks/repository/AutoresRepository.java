package com.aftermidnight.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aftermidnight.socialbooks.domain.Autor;

public interface AutoresRepository extends JpaRepository<Autor, Long>{

}
