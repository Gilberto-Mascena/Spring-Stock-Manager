package br.com.mascenadev.springstockmanager.controller;

import br.com.mascenadev.springstockmanager.model.fornecedor.dto.FornecedorRequestDTO;
import br.com.mascenadev.springstockmanager.model.fornecedor.dto.FornecedorResponseDTO;
import br.com.mascenadev.springstockmanager.service.FornecedorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
public class FornecedorController {

    private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @PostMapping("/fornecedores")
    public ResponseEntity<FornecedorResponseDTO> createFornecedor(@RequestBody @Valid FornecedorRequestDTO fornecedorRequestDTO) {

        FornecedorResponseDTO fornecedorSaved = fornecedorService.save(fornecedorRequestDTO);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(fornecedorSaved.getId())
                .toUri();
        return ResponseEntity.created(uri).body(fornecedorSaved);
    }

    @GetMapping("/fornecedores")
    public ResponseEntity<List<FornecedorResponseDTO>> getAllFornecedores() {
        return ResponseEntity.ok(fornecedorService.findAll());
    }

    @GetMapping("/fornecedores/{id}")
    public ResponseEntity<FornecedorResponseDTO> getFornecedorById(@PathVariable Long id) {
        return ResponseEntity.ok(fornecedorService.findById(id));
    }

    @PutMapping("/fornecedores/{id}")
    public ResponseEntity<FornecedorResponseDTO> updateFornecedor(@PathVariable Long id, @RequestBody @Valid FornecedorRequestDTO fornecedorRequestDTO) {
        return ResponseEntity.ok(fornecedorService.update(id, fornecedorRequestDTO));
    }

    @DeleteMapping("/fornecedores/{id}")
    public ResponseEntity<Void> deleteFornecedor(@PathVariable Long id) {
        fornecedorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
