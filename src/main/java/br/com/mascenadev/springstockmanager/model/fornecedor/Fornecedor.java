package br.com.mascenadev.springstockmanager.model.fornecedor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "fornecedores")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do fornecedor é obrigatório")
    @Size(min = 3, max = 255, message = "O nome do fornecedor deve ter entre 3 e 255 caracteres")
    @Column(nullable = false, length = 255)
    private String nome;

    @NotBlank(message = "O CNPJ do fornecedor é obrigatório")
    @Size(min = 14, max = 14, message = "O CNPJ do fornecedor deve ter exatamente 14 caracteres numéricos")
    @Column(nullable = false, length = 14, unique = true)
    @Pattern(
            regexp = "\\d{14}",
            message = "O CNPJ deve conter apenas 14 dígitos numéricos")
    private String cnpj;

    @NotBlank(message = "O endereço do fornecedor é obrigatório")
    @Size(min = 3, max = 255, message = "O endereço do fornecedor deve ter entre 3 e 255 caracteres")
    @Column(nullable = false, length = 255)
    private String endereco;

    @NotBlank(message = "O telefone 1 do fornecedor é obrigatório")
    @Size(min = 10, max = 15, message = "O telefone 1 do fornecedor deve ter entre 10 e 15 caracteres")
    @Column(nullable = false, length = 15)
    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}",
            message = "O telefone 1 deve estar no formato (XX) XXXX-XXXX ou (XX) XXXXX-XXXX")
    private String telefone1;

    @Size(max = 15, message = "O telefone 2 do fornecedor deve ter no máximo 15 caracteres")
    private String telefone2;

    public Fornecedor() {
    }

    public Fornecedor(Long id, String nome, String cnpj, String endereco, String telefone1, String telefone2) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Fornecedor that = (Fornecedor) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", cnpj='" + cnpj + '\'' +
               ", endereco='" + endereco + '\'' +
               ", telefone1='" + telefone1 + '\'' +
               ", telefone2='" + telefone2 + '\'' +
               '}';
    }
}
