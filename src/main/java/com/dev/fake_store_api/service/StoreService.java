package com.dev.fake_store_api.service;

import com.dev.fake_store_api.entity.ProdutosEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class StoreService {
    public ProdutosEntity[] retornarProdutos() {
        String url = "https://fakestoreapi.com";
        String uri = "/products";

        ProdutosEntity[] produtos = WebClient
                .create(url)
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(ProdutosEntity[].class).block();
        return produtos;
    }
}
