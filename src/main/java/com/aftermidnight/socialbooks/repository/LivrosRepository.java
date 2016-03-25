package com.aftermidnight.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aftermidnight.socialbooks.domain.Livro;

public interface LivrosRepository extends JpaRepository<Livro, Long>{

}
