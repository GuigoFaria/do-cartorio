package com.docartorio.controller;

import com.docartorio.entity.Cartorio;
import com.docartorio.entity.Certidao;
import com.docartorio.entity.form.CartorioForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/site/cartorios")
public class CartorioSiteController {

    @Autowired
    WebClient webClientCartorio;

    @Autowired
    WebClient webClientCertidao;

    @GetMapping
    public String cartorioPage(Model model) {
        Mono<List<Cartorio>> listMono = this.webClientCartorio
                .get()
                .uri("/cartorio")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Cartorio>>() {
                });

        List<Cartorio> cartoriosList = listMono.block();
        model.addAttribute("cartoriosList", cartoriosList);

        return "site/cartorios/index";
    }

    @GetMapping("/novo")
    public String createNewCartorioSite(@ModelAttribute("cartorioForm") CartorioForm cartorioForm, Model model) {

        Mono<Certidao[]> listMono = this.webClientCertidao
                .get()
                .uri("/api/v1/certidoes")
                .retrieve()
                .bodyToMono(Certidao[].class);

        Certidao[] certidaoList = listMono.block();
        List<String> certidaoStringList = new ArrayList<>();


        for (Certidao certidao : certidaoList) {
            certidaoStringList.add(certidao.getNome());
        }
        model.addAttribute("certidaoList", certidaoStringList);
        return "site/cartorios/formulario";
    }

    @PostMapping("/salvar")
    public String saveCartorio(@ModelAttribute("cartorioForm") CartorioForm cartorioForm){
        Mono<Cartorio> cartorioMono = this.webClientCartorio
                .method(HttpMethod.POST)
                .uri("/cartorio")
                .body(BodyInserters.fromValue(cartorioForm))
                .retrieve()
                .bodyToMono(Cartorio.class);

        Cartorio cartorio = cartorioMono.block();
        return "redirect:/site/cartorios";
    }

}
