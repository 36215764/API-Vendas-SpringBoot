package io.richsoftwares.vendasjpa.rest.controller;

import io.richsoftwares.vendasjpa.domain.entity.Cliente;
import io.richsoftwares.vendasjpa.domain.repository.Clientes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@Api("Api Clientes")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private Clientes clientes;

    public ClienteController(Clientes clientes) {
        this.clientes = clientes;
    }

    @ApiOperation("Obter detalhes de um cliente.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente encontrado."),
            @ApiResponse(code = 404, message = "Cliente não encontrado para o id informado.")})
    @GetMapping("{id}")
    public Cliente getClienteById( @PathVariable Integer id ){
        return clientes
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cliente não encontrado.")
                );
    }

    @ApiOperation("Salva um novo cliente.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente salvo com sucesso."),
            @ApiResponse(code = 400, message = "Erro de validação.")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente addCliente( @RequestBody @Valid Cliente cliente ){
        return clientes.save(cliente);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente( @PathVariable Integer id ){
        clientes.findById(id)
                .map(cliente -> {
                    clientes.delete(cliente);
                    return cliente;})
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cliente não encontrado")
                );

    }

    @PutMapping("{id}")
    public void updateCliente( @RequestBody Cliente cliente,
                                         @PathVariable Integer id ){
        clientes.findById(id)
                .map( clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clientes.save(cliente);
                    return clienteExistente;})
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cliente não encontrado")
                );
    }

    @GetMapping
    public List<Cliente> findCliente( Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );
        Example example = Example.of(filtro, matcher);
        return clientes.findAll(example);

    }
}
