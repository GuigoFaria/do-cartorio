package com.docartorio.entity.form;

import com.docartorio.entity.Cartorio;
import com.docartorio.entity.Certidao;
import com.docartorio.entity.Endereco;
import com.docartorio.repository.CartorioRepository;
import com.docartorio.repository.CertidaoRepository;
import com.docartorio.repository.EnderecoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartorioForm {
    private String nome;
    private String rua;
    private String numero;
    private String cep;
    private String bairro;
    private String cidade;
    private String estado;
    private String complemento;
    private List<String> nomesCertidao;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setNomesCertidao(List<String> nomesCertidao) {
        this.nomesCertidao = nomesCertidao;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }


    public Cartorio convert(CartorioRepository cartorioRepository, CertidaoRepository certidaoRepository) {
        List<Certidao> certidaoList = verifyCertidao(certidaoRepository);

        Endereco endereco = new Endereco(this.rua, this.numero, this.cep, this.bairro, this.cidade, this.estado, this.complemento);

        Cartorio cartorio = new Cartorio(this.nome, endereco, certidaoList);
        return cartorio;
    }

    public Cartorio update(Long id, CartorioRepository cartorioRepository, EnderecoRepository enderecoRepository, CertidaoRepository certidaoRepository) {
        Cartorio cartorio = cartorioRepository.getById(id);
        enderecoRepository.delete(cartorio.getEndereco());

        Endereco endereco = new Endereco(this.rua, this.numero, this.cep, this.bairro, this.cidade, this.estado, this.complemento);
        enderecoRepository.save(endereco);

        List<Certidao> certidaoList = verifyCertidao(certidaoRepository);

        cartorio.setEndereco(endereco);
        cartorio.setCertidaoList(certidaoList);
        cartorio.setNome(this.nome);

        return cartorio;
    }

    private List<Certidao> verifyCertidao(CertidaoRepository certidaoRepository) {
        List<Certidao> certidaoList = new ArrayList<>();
        this.nomesCertidao.forEach(nome -> {
            Optional<Certidao> certidaoOptional = certidaoRepository.findByNome(nome);
            if (certidaoOptional.isEmpty()) {
                Certidao certidao = new Certidao(nome);
                certidaoOptional = Optional.of(certidaoRepository.save(certidao));
            }
            certidaoList.add(certidaoOptional.get());
        });
        return certidaoList;
    }
}