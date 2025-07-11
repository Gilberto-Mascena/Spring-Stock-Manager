package br.com.mascenadev.springstockmanager.model.movimentacao.dto;

import br.com.mascenadev.springstockmanager.model.enums.TipoMovimentacao;
import br.com.mascenadev.springstockmanager.model.movimentacao.Movimentacao;
import br.com.mascenadev.springstockmanager.model.produto.dto.ProdutoResponseDTO;

import java.time.LocalDateTime;
import java.util.Objects;

public class MovimentacaoResponseDTO {

    private final Long id;
    private final ProdutoResponseDTO produto; // Adicionado: para incluir os detalhes do produto
    private final Integer quantidade;
    private final TipoMovimentacao tipoMovimentacao;
    private final LocalDateTime dataHora;

    public MovimentacaoResponseDTO(Movimentacao entity) {
        this.id = entity.getId();
        this.produto = new ProdutoResponseDTO(entity.getProduto());
        this.quantidade = entity.getQuantidade();
        this.tipoMovimentacao = entity.getTipoMovimentacao();
        this.dataHora = entity.getDataHora();
    }

    public MovimentacaoResponseDTO(Long id, ProdutoResponseDTO produto, Integer quantidade, TipoMovimentacao tipoMovimentacao, LocalDateTime dataHora) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.tipoMovimentacao = tipoMovimentacao;
        this.dataHora = dataHora;
    }

    public Long getId() {
        return id;
    }

    public ProdutoResponseDTO getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovimentacaoResponseDTO that = (MovimentacaoResponseDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MovimentacaoResponseDTO{" +
               "id=" + id +
               ", produto=" + produto + // Incluí o produto no toString
               ", quantidade=" + quantidade +
               ", tipoMovimentacao=" + tipoMovimentacao +
               ", dataHora=" + dataHora +
               '}';
    }
}