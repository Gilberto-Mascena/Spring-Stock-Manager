package br.com.mascenadev.springstockmanager.repository;

import br.com.mascenadev.springstockmanager.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    Optional<Fornecedor> findByCnpj(String cnpj);
    List<Fornecedor> findByNomeContainingIgnoreCase(String nome);
}
