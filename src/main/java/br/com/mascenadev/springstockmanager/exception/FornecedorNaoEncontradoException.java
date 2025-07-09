package br.com.mascenadev.springstockmanager.exception;

public class FornecedorNaoEncontradoException extends RuntimeException {
    public FornecedorNaoEncontradoException() {
        super("Fornecedor não encontrado");
    }

    public FornecedorNaoEncontradoException(Long id) {
        super("Fornecedor não encontrado com o ID: " + id);
    }

    public FornecedorNaoEncontradoException(String nome) {
        super("Fornecedor não encontrado com o nome: " + nome);
    }
}
