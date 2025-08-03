package br.com.mascenadev.springstockmanager.controller;

import br.com.mascenadev.springstockmanager.model.fornecedor.dto.FornecedorRequestDTO;
import br.com.mascenadev.springstockmanager.model.fornecedor.dto.FornecedorResponseDTO;
import br.com.mascenadev.springstockmanager.service.FornecedorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
public class FornecedorController {

    private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @Operation(summary = "Create a new fornecedor")
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

    @Operation(summary = "Get all fornecedores")
    @GetMapping("/fornecedores")
    public ResponseEntity<List<FornecedorResponseDTO>> getAllFornecedores() {
        return ResponseEntity.ok(fornecedorService.findAll());
    }

    @Operation(summary = "Get a fornecedor by ID")
    @GetMapping("/fornecedores/{id}")
    public ResponseEntity<FornecedorResponseDTO> getFornecedorById(@PathVariable Long id) {
        return ResponseEntity.ok(fornecedorService.findById(id));
    }

    @Operation(summary = "Update a fornecedor by ID")
    @PutMapping("/fornecedores/{id}")
    public ResponseEntity<FornecedorResponseDTO> updateFornecedor(@PathVariable Long id, @RequestBody @Valid FornecedorRequestDTO fornecedorRequestDTO) {
        return ResponseEntity.ok(fornecedorService.update(id, fornecedorRequestDTO));
    }

    @Operation(summary = "Delete a fornecedor by ID")
    @DeleteMapping("/fornecedores/{id}")
    public ResponseEntity<Void> deleteFornecedor(@PathVariable Long id) {
        fornecedorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
