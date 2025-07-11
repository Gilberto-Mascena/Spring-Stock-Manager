package br.com.mascenadev.springstockmanager.model.movimentacao.dto;

import br.com.mascenadev.springstockmanager.model.enums.TipoMovimentacao;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class MovimentacaoRequestDTO {

    @NotNull(message = "O ID do produto não pode ser nulo.")
    private Long produtoId;

    @NotNull(message = "A quantidade não pode ser nula.")
    @Positive(message = "A quantidade deve ser um valor positivo maior que zero.") // Alterado de @Min(0) para @Positive
    private Integer quantidade;

    @NotNull(message = "O tipo de movimentação não pode ser nulo.")
    private TipoMovimentacao tipoMovimentacao;

    private LocalDateTime dataHora;

    public MovimentacaoRequestDTO() {
        this.dataHora = LocalDateTime.now();
    }

    public MovimentacaoRequestDTO(Long produtoId, Integer quantidade, TipoMovimentacao tipoMovimentacao, LocalDateTime dataHora) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.tipoMovimentacao = tipoMovimentacao;
        this.dataHora = dataHora;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public String toString() {
        return "MovimentacaoRequestDTO{" +
               "produtoId=" + produtoId +
               ", quantidade=" + quantidade +
               ", tipoMovimentacao=" + tipoMovimentacao +
               ", dataHora=" + dataHora +
               '}';
    }
}