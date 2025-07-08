package br.com.mascenadev.springstockmanager.model;

import br.com.mascenadev.springstockmanager.model.enums.TipoMovimentacao;
import br.com.mascenadev.springstockmanager.model.produto.Produto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "movimentacoes")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @NotNull(message = "A quantidade não pode ser nula")
    @Min(value = 0, message = "A quantidade deve ser maior ou igual a zero")
    @Column(nullable = false)
    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipo;

    @NotNull(message = "A data e hora da movimentação não podem ser nulas")
    @Column(nullable = false)
    private LocalDateTime dataHora;

    public Movimentacao() {
        this.dataHora = LocalDateTime.now();
    }

    public Movimentacao(Long id, Produto produto, Integer quantidade, TipoMovimentacao tipo, LocalDateTime dataHora) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.dataHora = dataHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Movimentacao that = (Movimentacao) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Movimentacao{" +
               "id=" + id +
               ", produto=" + produto +
               ", quantidade=" + quantidade +
               ", tipo=" + tipo +
               ", dataHora=" + dataHora +
               '}';
    }
}
