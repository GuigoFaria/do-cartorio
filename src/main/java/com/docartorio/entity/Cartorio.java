package com.docartorio.entity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
@Entity
public class Cartorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String nome;
    @OneToOne(fetch = FetchType.EAGER)
    private Endereco endereco;
    @ManyToMany
    private List<CertidaoDto> certidaoDtoList;

    public Cartorio(String nome, Endereco endereco, List<CertidaoDto> certidaoDtoList) {
        this.nome = nome;
        this.endereco = endereco;
        this.certidaoDtoList = certidaoDtoList;
    }

    public UUID getId() {
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

    public List<CertidaoDto> getCertidaoDtoList() {
        return certidaoDtoList;
    }

    public void setCertidaoDtoList(List<CertidaoDto> certidaoDtoList) {
        this.certidaoDtoList = certidaoDtoList;
    }
}
