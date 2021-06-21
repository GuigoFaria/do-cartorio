package com.docartorio.repository;

import com.docartorio.entity.Cartorio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartorioRepository extends JpaRepository<Cartorio, Long> {
    Optional<Cartorio> findByNome(String nome);
}
