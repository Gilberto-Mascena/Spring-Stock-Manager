package br.com.mascenadev.springstockmanager.model.fornecedor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class FornecedorRequestDTO {

    private Long id;

    @NotBlank(message = "O nome do fornecedor é obrigatório")
    @Size(min = 3, max = 255, message = "O nome do fornecedor deve ter entre 3 e 255 caracteres")
    private String nome;

    @NotBlank(message = "O CNPJ do fornecedor é obrigatório")
    @Size(min = 18, max = 18, message = "O CNPJ do fornecedor deve ter exatamente 18 caracteres, incluindo pontos e traços)")
    @Pattern(
            regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}",
            message = "O CNPJ deve estar no formato XX.XXX.XXX/XXXX-XX")
    private String cnpj;

    @NotBlank(message = "O endereço do fornecedor é obrigatório")
    @Size(min = 3, max = 255, message = "O endereço do fornecedor deve ter entre 3 e 255 caracteres")
    private String endereco;

    @NotBlank(message = "O telefone 1 do fornecedor é obrigatório")
    @Size(min = 14, max = 15, message = "O telefone 1 do fornecedor deve ter entre 10 e 15 caracteres")
    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}",
            message = "O telefone 1 deve ter entre 14 e 15 caracteres (ex: (XX) XXXX-XXXX ou (XX) XXXXX-XXXX)")
    private String telefone1;

    @Size(max = 15, message = "O telefone 2 do fornecedor deve ter no máximo 15 caracteres")
    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "O telefone 2 deve estar no formato (XX) XXXX-XXXX ou (XX) XXXXX-XXXX")
    private String telefone2;

    public FornecedorRequestDTO() {
    }

    public FornecedorRequestDTO(Long id, String nome, String cnpj, String endereco, String telefone1, String telefone2) {
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
        FornecedorRequestDTO that = (FornecedorRequestDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "FornecedorRequestDTO{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", cnpj='" + cnpj + '\'' +
               ", endereco='" + endereco + '\'' +
               ", telefone1='" + telefone1 + '\'' +
               ", telefone2='" + telefone2 + '\'' +
               '}';
    }
}
