package com.docartorio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;


@Entity
public class CertidaoDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String nome;

    public CertidaoDto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
