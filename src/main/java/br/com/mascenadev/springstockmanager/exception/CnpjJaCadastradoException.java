package br.com.mascenadev.springstockmanager.exception;

public class CnpjJaCadastradoException extends RuntimeException {
    public CnpjJaCadastradoException() {
        super("CNPJ já cadastrado para outro fornecedor.");
    }
    public CnpjJaCadastradoException(String cnpj) {
        super("CNPJ já cadastrado já castrado para outro fornecedor: " + cnpj);
    }
}
