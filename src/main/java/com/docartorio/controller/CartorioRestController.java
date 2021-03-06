package com.docartorio.controller;

import com.docartorio.entity.Cartorio;
import com.docartorio.entity.form.CartorioForm;
import com.docartorio.repository.CartorioRepository;
import com.docartorio.repository.CertidaoRepository;
import com.docartorio.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cartorio")
public class CartorioRestController {

    @Autowired
    private CartorioRepository cartorioRepository;

    @Autowired
    private CertidaoRepository certidaoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Cartorio> createCartorio(@RequestBody CartorioForm cartorioForm, UriComponentsBuilder uriBuilder) {
        Cartorio cartorio = cartorioForm.convert(cartorioRepository, certidaoRepository);
        if (cartorioRepository.findByNome(cartorio.getNome()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        enderecoRepository.save(cartorio.getEndereco());
        cartorioRepository.save(cartorio);
        URI uri = uriBuilder.path("/cartorio/{id}").buildAndExpand(cartorio.getId()).toUri();
        return ResponseEntity.created(uri).body(cartorio);
    }

    @GetMapping
    public ResponseEntity<List<Cartorio>> findAllCartorios() {
        List<Cartorio> cartorioList = cartorioRepository.findAll();
        return ResponseEntity.ok(cartorioList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cartorio> detalhar(@PathVariable Long id) {
        Optional<Cartorio> cartorio = cartorioRepository.findById(id);
        if (cartorio.isPresent()) {
            return ResponseEntity.ok(cartorio.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Cartorio> atualizar(@PathVariable Long id, @RequestBody CartorioForm cartorioForm) {
        Optional<Cartorio> optional = cartorioRepository.findById(id);
        if (optional.isPresent()) {
            Cartorio cartorio = cartorioForm.update(id, cartorioRepository, enderecoRepository, certidaoRepository);
            return ResponseEntity.ok(cartorio);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Cartorio> optional = cartorioRepository.findById(id);
        if (optional.isPresent()) {
            cartorioRepository.deleteById(id);
            enderecoRepository.deleteById(optional.get().getEndereco().getId());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}