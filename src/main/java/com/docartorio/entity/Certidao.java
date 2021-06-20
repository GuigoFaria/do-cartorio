package com.docartorio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Certidao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Certidao(String nome) {
        this.nome = nome;
    }

    public Certidao() {
    }

    public String getNome() {
        return nome;
    }
}
