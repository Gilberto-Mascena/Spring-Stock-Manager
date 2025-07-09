package br.com.mascenadev.springstockmanager.model.produto;

import br.com.mascenadev.springstockmanager.model.fornecedor.Fornecedor;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do produto não pode estar em branco")
    @Size(min = 3, max = 255, message = "O nome do produto deve ter entre 3 e 255 caracteres")
    @Column(nullable = false, length = 255)
    private String nome;

    @NotBlank(message = "A descrição do produto não pode estar em branco")
    @Size(min = 3, max = 255, message = "A descrição do produto deve ter entre 3 e 255 caracteres")
    @Column(nullable = false, length = 255)
    private String descricao;

    @NotNull(message = "O preço unitário do produto não pode ser nulo")
    @Column(nullable = false, precision = 10, scale = 2)
    @PositiveOrZero(message = "O preço unitário do produto deve ser positivo ou zero")
    private BigDecimal precoUnitario;

    @NotNull(message = "A quantidade em estoque do produto não pode ser nula")
    @Min(value = 0, message = "A quantidade em estoque não pode ser negativa")
    @Column(nullable = false)
    private Integer quantidadeEmEstoque;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private Fornecedor fornecedor;

    public Produto() {
    }

    public Produto(Long id, String nome, String descricao, BigDecimal precoUnitario, Integer quantidadeEmEstoque, Fornecedor fornecedor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.fornecedor = fornecedor;
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

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Produto{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", descricao='" + descricao + '\'' +
               ", precoUnitario=" + precoUnitario +
               ", quantidadeEmEstoque=" + quantidadeEmEstoque +
               ", fornecedor=" + fornecedor +
               '}';
    }
}
