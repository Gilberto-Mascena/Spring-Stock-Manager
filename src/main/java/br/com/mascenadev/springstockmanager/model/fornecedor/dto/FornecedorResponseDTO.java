package br.com.mascenadev.springstockmanager.model.fornecedor.dto;

import br.com.mascenadev.springstockmanager.model.fornecedor.Fornecedor;

import java.util.Objects;

public class FornecedorResponseDTO {

    private final Long id;
    private final String nome;
    private final String cnpj;
    private final String endereco;
    private final String telefone1;
    private final String telefone2;

    public FornecedorResponseDTO(Fornecedor entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.cnpj = entity.getCnpj();
        this.endereco = entity.getEndereco();
        this.telefone1 = entity.getTelefone1();
        this.telefone2 = entity.getTelefone2();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FornecedorResponseDTO that = (FornecedorResponseDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "FornecedorResponseDTO{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", cnpj='" + cnpj + '\'' +
               ", endereco='" + endereco + '\'' +
               ", telefone1='" + telefone1 + '\'' +
               ", telefone2='" + telefone2 + '\'' +
               '}';
    }
}
