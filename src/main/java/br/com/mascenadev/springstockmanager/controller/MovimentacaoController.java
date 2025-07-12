package br.com.mascenadev.springstockmanager.controller;

import br.com.mascenadev.springstockmanager.model.movimentacao.dto.MovimentacaoRequestDTO;
import br.com.mascenadev.springstockmanager.model.movimentacao.dto.MovimentacaoResponseDTO;
import br.com.mascenadev.springstockmanager.service.MovimentacaoService;
import br.com.mascenadev.springstockmanager.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    // Sugestão de melhoria para o create
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

    @GetMapping("/movimentacoes")
    public ResponseEntity<List<MovimentacaoResponseDTO>> findAll() {
        List<MovimentacaoResponseDTO> movimentacoes = movimentacaoService.findAll();
        return ResponseEntity.ok(movimentacoes);
    }

    @GetMapping("/movimentacoes/{id}")
    public ResponseEntity<MovimentacaoResponseDTO> findById(@PathVariable Long id) {
        MovimentacaoResponseDTO movimentacao = movimentacaoService.findById(id);
        return ResponseEntity.ok(movimentacao);
    }

    @GetMapping("/movimentacoes/relatorio")
    public ResponseEntity<List<MovimentacaoResponseDTO>> findByProdutoId(@RequestParam Long produtoId) {
        List<MovimentacaoResponseDTO> movimentacoes = movimentacaoService.findByProdutoId(produtoId);
        return ResponseEntity.ok(movimentacoes);
    }

    @PutMapping("/movimentacoes/{id}")
    public ResponseEntity<MovimentacaoResponseDTO> update(@PathVariable Long id, @RequestBody @Valid MovimentacaoRequestDTO movimentacaoRequestDTO) {
        MovimentacaoResponseDTO updatedMovimentacao = movimentacaoService.update(id, movimentacaoRequestDTO);
        return ResponseEntity.ok(updatedMovimentacao);
    }

    @DeleteMapping("/movimentacoes/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        movimentacaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
