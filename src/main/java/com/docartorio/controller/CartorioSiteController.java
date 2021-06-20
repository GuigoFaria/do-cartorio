package com.docartorio.controller;

import com.docartorio.entity.Cartorio;
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
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
@RequestMapping("/site/cartorios")
public class CartorioSiteController {

    @Autowired
    WebClient webClient;

    @GetMapping
    public String cartorioPage(Model model) {
        Mono<List<Cartorio>> listMono = this.webClient
                .method(HttpMethod.GET)
                .uri("/cartorio")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Cartorio>>() {
                });

        List<Cartorio> cartoriosList = listMono.block();
        model.addAttribute("cartoriosList", cartoriosList);

        return "site/cartorios/index";
    }

    @GetMapping("/novo")
    public String createNewCartorioSite (@ModelAttribute CartorioForm cartorioForm){
        return "site/cartorios/form";
    }
}
