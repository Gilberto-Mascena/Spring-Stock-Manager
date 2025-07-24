package br.com.mascenadev.springstockmanager.exception;

public class EstoqueInsuficienteException extends RuntimeException {
    public EstoqueInsuficienteException() {
        super("Estoque insuficiente para o produto");
    }

    public EstoqueInsuficienteException(Long idProduto) {
        super("Estoque insuficiente para o produto: " + idProduto);
    }

    public EstoqueInsuficienteException(String nome) {
        super("Estoque insuficiente para o produto: " + nome);
    }
}
