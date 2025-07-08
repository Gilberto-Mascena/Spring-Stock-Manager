package br.com.mascenadev.springstockmanager.model.produto.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Objects;

public class ProdutoRequestDTO {

    private Long id;

    @NotBlank(message = "O nome do produto não pode estar em branco")
    @Size(min = 3, max = 255, message = "O nome do produto deve ter entre 3 e 255 caracteres")
    private String nome;

    @NotBlank(message = "A descrição do produto não pode estar em branco")
    @Size(min = 3, max = 255, message = "A descrição do produto deve ter entre 3 e 255 caracteres")
    private String descricao;

    @NotNull(message = "O preço unitário do produto não pode ser nulo")
    @PositiveOrZero(message = "O preço unitário do produto deve ser positivo ou zero")
    private BigDecimal precoUnitario;

    @NotNull(message = "A quantidade em estoque do produto não pode ser nula")
    @Min(value = 0, message = "A quantidade em estoque não pode ser negativa")
    private Integer quantidadeEmEstoque;

    @NotNull(message = "O ID do fornecedor não pode ser nulo")
    private Long fornecedorId;

    public ProdutoRequestDTO() {
    }

    public ProdutoRequestDTO(Long id, String nome, String descricao, BigDecimal precoUnitario, Integer quantidadeEmEstoque, Long fornecedorId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.fornecedorId = fornecedorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Integer getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(Integer quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public Long getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Long fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoRequestDTO that = (ProdutoRequestDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
