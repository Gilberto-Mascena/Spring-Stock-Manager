package br.com.mascenadev.springstockmanager.exception;

public class MovimentacaoNaoEncontradaException extends RuntimeException {
    public MovimentacaoNaoEncontradaException() {
        super("Movimentação não encontrada");
    }
    public MovimentacaoNaoEncontradaException(Long id) {
        super("Movimentação não encontrada com o ID: " + id);
    }
}
