package br.com.mascenadev.springstockmanager.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {

    public ProdutoNaoEncontradoException() {
        super("Produto não encontrado");
    }

    public ProdutoNaoEncontradoException(Long produtoId) {
        super("Produto não encontrado com o ID: " + produtoId);
    }

    public ProdutoNaoEncontradoException(String nome) {
        super("Produto não encontrado com o nome: " + nome);
    }
}
