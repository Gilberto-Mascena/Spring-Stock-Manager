package br.com.mascenadev.springstockmanager.controller;

import br.com.mascenadev.springstockmanager.model.movimentacao.dto.MovimentacaoRequestDTO;
import br.com.mascenadev.springstockmanager.model.movimentacao.dto.MovimentacaoResponseDTO;
import br.com.mascenadev.springstockmanager.service.MovimentacaoService;
import br.com.mascenadev.springstockmanager.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
public class MovimentacaoController {

    public final MovimentacaoService movimentacaoService;
    public final ProdutoService produtoService;

    public MovimentacaoController(MovimentacaoService movimentacaoService, ProdutoService produtoService) {
        this.movimentacaoService = movimentacaoService;
        this.produtoService = produtoService;
    }

    @Operation(summary = "Create a new movimentacao")
    @PostMapping("/movimentacoes")
    public ResponseEntity<MovimentacaoResponseDTO> create(@RequestBody @Valid MovimentacaoRequestDTO movimentacaoRequestDTO) {
        MovimentacaoResponseDTO movimentacaoResponseDTO = movimentacaoService.create(movimentacaoRequestDTO);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(movimentacaoResponseDTO.getId())
                .toUri();
        return ResponseEntity.created(uri).body(movimentacaoResponseDTO);
    }

    @Operation(summary = "Get all movimentacoes")
    @GetMapping("/movimentacoes")
    public ResponseEntity<List<MovimentacaoResponseDTO>> findAll() {
        List<MovimentacaoResponseDTO> movimentacoes = movimentacaoService.findAll();
        return ResponseEntity.ok(movimentacoes);
    }

    @Operation(summary = "Get a movimentacao by ID")
    @GetMapping("/movimentacoes/{id}")
    public ResponseEntity<MovimentacaoResponseDTO> findById(@PathVariable Long id) {
        MovimentacaoResponseDTO movimentacao = movimentacaoService.findById(id);
        return ResponseEntity.ok(movimentacao);
    }

    @Operation(summary = "Get movimentacoes by produto ID")
    @GetMapping("/movimentacoes/relatorio")
    public ResponseEntity<List<MovimentacaoResponseDTO>> findByProdutoId(@RequestParam Long produtoId) {
        List<MovimentacaoResponseDTO> movimentacoes = movimentacaoService.findByProdutoId(produtoId);
        return ResponseEntity.ok(movimentacoes);
    }

    @Operation(summary = "Update a movimentacao by ID")
    @PutMapping("/movimentacoes/{id}")
    public ResponseEntity<MovimentacaoResponseDTO> update(@PathVariable Long id, @RequestBody @Valid MovimentacaoRequestDTO movimentacaoRequestDTO) {
        MovimentacaoResponseDTO updatedMovimentacao = movimentacaoService.update(id, movimentacaoRequestDTO);
        return ResponseEntity.ok(updatedMovimentacao);
    }

    @Operation(summary = "Delete a movimentacao by ID")
    @DeleteMapping("/movimentacoes/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        movimentacaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
