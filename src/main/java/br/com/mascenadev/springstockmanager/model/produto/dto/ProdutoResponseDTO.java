package br.com.mascenadev.springstockmanager.model.produto.dto;

import br.com.mascenadev.springstockmanager.model.produto.Produto;

import java.math.BigDecimal;
import java.util.Objects;

public class ProdutoResponseDTO {

    private final Long id;
    private final String nome;
    private final String descricao;
    private final BigDecimal precoUnitario;
    private final Integer quantidadeEmEstoque;
    private final Long fornecedorId;

    public ProdutoResponseDTO(Produto entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.descricao = entity.getDescricao();
        this.precoUnitario = entity.getPrecoUnitario();
        this.quantidadeEmEstoque = entity.getQuantidadeEmEstoque();
        this.fornecedorId = entity.getFornecedor() != null ? entity.getFornecedor().getId() : null;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public Integer getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public Long getFornecedorId() {
        return fornecedorId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoResponseDTO that = (ProdutoResponseDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProdutoResponseDTO{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", descricao='" + descricao + '\'' +
               ", precoUnitario='" + precoUnitario + '\'' +
               ", quantidadeEmEstoque=" + quantidadeEmEstoque +
               ", fornecedorId=" + fornecedorId +
               '}';
    }
}
