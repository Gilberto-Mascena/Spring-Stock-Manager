package br.com.mascenadev.springstockmanager.controller;

import br.com.mascenadev.springstockmanager.model.produto.dto.ProdutoRequestDTO;
import br.com.mascenadev.springstockmanager.model.produto.dto.ProdutoResponseDTO;
import br.com.mascenadev.springstockmanager.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/produtos")
    public ResponseEntity<ProdutoResponseDTO> createProduto(@RequestBody @Valid ProdutoRequestDTO produtoRequestDTO) {
        ProdutoResponseDTO produtoSaved = produtoService.save(produtoRequestDTO);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(produtoSaved.getId())
                .toUri();
        return ResponseEntity.created(uri).body(produtoSaved);
    }

    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoResponseDTO>> getAllProdutos() {
        return ResponseEntity.ok(produtoService.findAll());
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<ProdutoResponseDTO> getProdutoById(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.findById(id));
    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<ProdutoResponseDTO> updateProduto(@PathVariable Long id, @RequestBody @Valid ProdutoRequestDTO produtoRequestDTO) {
        return ResponseEntity.ok(produtoService.update(id, produtoRequestDTO));
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
