package br.com.mascenadev.springstockmanager.service;

import br.com.mascenadev.springstockmanager.exception.EstoqueInsuficienteException;
import br.com.mascenadev.springstockmanager.exception.MovimentacaoNaoEncontradaException;
import br.com.mascenadev.springstockmanager.exception.ProdutoNaoEncontradoException;
import br.com.mascenadev.springstockmanager.model.enums.TipoMovimentacao;
import br.com.mascenadev.springstockmanager.model.movimentacao.Movimentacao;
import br.com.mascenadev.springstockmanager.model.movimentacao.dto.MovimentacaoRequestDTO;
import br.com.mascenadev.springstockmanager.model.movimentacao.dto.MovimentacaoResponseDTO;
import br.com.mascenadev.springstockmanager.model.produto.Produto;
import br.com.mascenadev.springstockmanager.repository.MovimentacaoRepository;
import br.com.mascenadev.springstockmanager.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final ProdutoRepository produtoRepository;

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository, ProdutoRepository produtoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public MovimentacaoResponseDTO create(MovimentacaoRequestDTO requestDTO) {
        Produto produto = produtoRepository.findById(requestDTO.getProdutoId())
                .orElseThrow(() -> new ProdutoNaoEncontradoException(requestDTO.getProdutoId()));

        Movimentacao novaMovimentacao = new Movimentacao();
        novaMovimentacao.setProduto(produto);
        novaMovimentacao.setQuantidade(requestDTO.getQuantidade());
        novaMovimentacao.setTipoMovimentacao(requestDTO.getTipoMovimentacao());
        novaMovimentacao.setDataHora(requestDTO.getDataHora() != null ? requestDTO.getDataHora() : LocalDateTime.now());

        if (novaMovimentacao.getTipoMovimentacao() == TipoMovimentacao.ENTRADA) {
            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() + novaMovimentacao.getQuantidade());
        } else if (novaMovimentacao.getTipoMovimentacao() == TipoMovimentacao.SAIDA) {
            if (produto.getQuantidadeEmEstoque() < novaMovimentacao.getQuantidade()) {
                throw new EstoqueInsuficienteException(produto.getNome());
            }
            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - novaMovimentacao.getQuantidade());
        }

        produtoRepository.save(produto);
        Movimentacao savedMovimentacao = movimentacaoRepository.save(novaMovimentacao);
        return new MovimentacaoResponseDTO(savedMovimentacao);
    }

    public List<MovimentacaoResponseDTO> findAll() {
        List<Movimentacao> movimentacoes = movimentacaoRepository.findAll();
        return movimentacoes.stream()
                .map(MovimentacaoResponseDTO::new)
                .toList();
    }

    public MovimentacaoResponseDTO findById(Long id) {
        Movimentacao movimentacao = movimentacaoRepository.findById(id)
                .orElseThrow(() -> new MovimentacaoNaoEncontradaException(id));
        return new MovimentacaoResponseDTO(movimentacao);
    }

    public List<MovimentacaoResponseDTO> findByProdutoId(Long produtoId) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));

        List<Movimentacao> movimentacoes = movimentacaoRepository.findByProduto(produto);

        return movimentacoes.stream()
                .map(MovimentacaoResponseDTO::new)
                .toList();
    }

    public List<MovimentacaoResponseDTO> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim) {
        List<Movimentacao> movimentacoes = movimentacaoRepository.findByDataHoraBetween(inicio, fim);
        return movimentacoes.stream()
                .map(MovimentacaoResponseDTO::new)
                .toList();
    }

    public List<MovimentacaoResponseDTO> findByTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        List<Movimentacao> movimentacoes = movimentacaoRepository.findByTipoMovimentacao(tipoMovimentacao);
        return movimentacoes.stream()
                .map(MovimentacaoResponseDTO::new)
                .toList();
    }

    @Transactional
    public MovimentacaoResponseDTO update(Long id, MovimentacaoRequestDTO requestDTO) {
        Movimentacao existingMovimentacao = movimentacaoRepository.findById(id)
                .orElseThrow(() -> new MovimentacaoNaoEncontradaException(id));

        existingMovimentacao.setQuantidade(requestDTO.getQuantidade());
        existingMovimentacao.setTipoMovimentacao(requestDTO.getTipoMovimentacao());
        existingMovimentacao.setDataHora(requestDTO.getDataHora() != null ? requestDTO.getDataHora() : existingMovimentacao.getDataHora());

        if (requestDTO.getProdutoId() != null && !existingMovimentacao.getProduto().getId().equals(requestDTO.getProdutoId())) {
            throw new IllegalArgumentException("Não é permitido alterar o produto de uma movimentação existente. Considere estornar e criar uma nova.");
        }

        Movimentacao updatedMovimentacao = movimentacaoRepository.save(existingMovimentacao);
        return new MovimentacaoResponseDTO(updatedMovimentacao);
    }

    @Transactional
    public void deleteById(Long id) {
        Movimentacao movimentacao = movimentacaoRepository.findById(id)
                .orElseThrow(() -> new MovimentacaoNaoEncontradaException(id));
        movimentacaoRepository.delete(movimentacao);
    }
}