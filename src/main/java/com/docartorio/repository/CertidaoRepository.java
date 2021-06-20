package com.docartorio.repository;

import com.docartorio.entity.Certidao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CertidaoRepository extends JpaRepository<Certidao, Long> {
    Optional<Certidao> findByNome(String nome);
}
