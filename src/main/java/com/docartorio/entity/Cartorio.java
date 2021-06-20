package com.docartorio.entity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
@Entity
public class Cartorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToOne(fetch = FetchType.EAGER)
    private Endereco endereco;
    @ManyToMany
    private List<Certidao> certidaoList;

    public Cartorio(String nome, Endereco endereco, List<Certidao> certidaoList) {
        this.nome = nome;
        this.endereco = endereco;
        this.certidaoList = certidaoList;
    }

    public Cartorio() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Certidao> getCertidaoList() {
        return certidaoList;
    }

    public void setCertidaoList(List<Certidao> certidaoList) {
        this.certidaoList = certidaoList;
    }
}
