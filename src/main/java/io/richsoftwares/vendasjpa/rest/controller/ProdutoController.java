package io.richsoftwares.vendasjpa.rest.controller;

import io.richsoftwares.vendasjpa.domain.entity.Produto;
import io.richsoftwares.vendasjpa.domain.repository.Produtos;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private Produtos repository;

    public ProdutoController( Produtos produtos) {
        this.repository = produtos;
    }

    @GetMapping("{id}")
    public Produto getProdutoById(@PathVariable Integer id ){
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "Produto não encontrado")
                );
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Produto addProduto( @RequestBody @Valid Produto produto ){
        return repository.save(produto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteProduto( @PathVariable Integer id ){
        repository.findById(id)
                .map(produto -> {
                    repository.delete(produto);
                    return produto;})
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "Produto não encontrado")
                );

    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateProduto( @RequestBody Produto produto,
                               @PathVariable Integer id ){
        repository.findById(id)
                .map( p -> {
                    produto.setId(p.getId());
                    repository.save(produto);
                    return p;})
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "Produto não encontrado")
                );
    }

    @GetMapping
    public List<Produto> findProduto(Produto filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );
        Example example = Example.of(filtro, matcher);
        return repository.findAll(example);

    }
}
