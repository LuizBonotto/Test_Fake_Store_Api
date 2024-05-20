package com.dev.fake_store_api.controller;

import com.dev.fake_store_api.entity.ProdutosEntity;
import com.dev.fake_store_api.repository.ProdutosRepository;
import com.dev.fake_store_api.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping
public class ProdutosController {
    @Autowired
    private StoreService storeService;

    @Autowired
    private ProdutosRepository produtosRepository;

    @GetMapping("/inicializar")
    public ResponseEntity inicializarBanco() {
        ProdutosEntity[] produtos = storeService.retornarProdutos();
        for (ProdutosEntity p : produtos) {
            produtosRepository.insert(p);
        }
        return ResponseEntity.ok(produtos);
    }
    @GetMapping("/produtos")
    public ResponseEntity get() {
        return new ResponseEntity<>(produtosRepository.getAll(), HttpStatus.OK);
    }

    @GetMapping("/show")
    public String exibirProdutos(Model model) {
        // Busque os produtos do banco de dados
        List<ProdutosEntity> produtos = produtosRepository.getAll();

        // Adicione os produtos ao modelo
        model.addAttribute("produtos", produtos);

        // Retorne o nome da página HTML criada anteriormente (produtos.html)
        return "produtos";
    }
    @GetMapping("/show/{category}")
    public String exibirProdutosCategoria(Model model, @PathVariable String category) {
        List<ProdutosEntity> produtos = produtosRepository.getByCategory(category+"%");
        model.addAttribute("produtos", produtos);
        return "produtos";
    }

    @GetMapping("/showOne/{id}")
    public String exibirProdutoId(Model model, @PathVariable Long id) {
        ProdutosEntity produtos = produtosRepository.getById(id);
        model.addAttribute("produtos", produtos);
        return "umProduto";
    }


    @PostMapping
    public ResponseEntity post (@RequestBody ProdutosEntity produtos) {
        try {
            return new ResponseEntity<>(produtosRepository.insert(produtos), HttpStatus.CREATED);
        } catch (Exception error) {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/prdutos/{id}")
    public ResponseEntity delete (@PathVariable Long id) {
        try {
            return new ResponseEntity<>(produtosRepository.remove(id), HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/alunos")
    public ResponseEntity put(@RequestBody ProdutosEntity produtos) {
        try {
            return new ResponseEntity<>(produtosRepository.update(produtos), HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.NO_CONTENT);
        }
    }
}
