package br.com.mascenadev.springstockmanager.repository;

import br.com.mascenadev.springstockmanager.model.enums.TipoMovimentacao;
import br.com.mascenadev.springstockmanager.model.movimentacao.Movimentacao;
import br.com.mascenadev.springstockmanager.model.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    List<Movimentacao> findByProduto(Produto produto);

    List<Movimentacao> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);

    List<Movimentacao> findByTipoMovimentacao(TipoMovimentacao tipoMovimentacao);
}
