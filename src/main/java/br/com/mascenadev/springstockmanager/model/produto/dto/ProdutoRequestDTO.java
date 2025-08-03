package br.com.mascenadev.springstockmanager.model.produto.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class ProdutoRequestDTO {

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

    public ProdutoRequestDTO(String nome, String descricao, BigDecimal precoUnitario, Integer quantidadeEmEstoque, Long fornecedorId) {
        this.nome = nome;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.fornecedorId = fornecedorId;
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
    public String toString() {
        return "ProdutoRequestDTO{" +
               ", nome='" + nome + '\'' +
               ", descricao='" + descricao + '\'' +
               ", precoUnitario=" + precoUnitario +
               ", quantidadeEmEstoque=" + quantidadeEmEstoque +
               ", fornecedorId=" + fornecedorId +
               '}';
    }
}
