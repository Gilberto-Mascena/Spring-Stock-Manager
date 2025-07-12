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
    private final ProdutoRepository produtoRepository; // Injete ProdutoRepository

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository, ProdutoRepository produtoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public MovimentacaoResponseDTO create(MovimentacaoRequestDTO requestDTO) { // Renomeado para 'create' para clareza
        Produto produto = produtoRepository.findById(requestDTO.getProdutoId())
                .orElseThrow(() -> new ProdutoNaoEncontradoException(requestDTO.getProdutoId()));

        Movimentacao novaMovimentacao = new Movimentacao();
        novaMovimentacao.setProduto(produto); // Associa o objeto Produto completo
        novaMovimentacao.setQuantidade(requestDTO.getQuantidade());
        novaMovimentacao.setTipoMovimentacao(requestDTO.getTipoMovimentacao());
        novaMovimentacao.setDataHora(requestDTO.getDataHora() != null ? requestDTO.getDataHora() : LocalDateTime.now());

        // Lógica de ajuste de estoque
        if (novaMovimentacao.getTipoMovimentacao() == TipoMovimentacao.ENTRADA) {
            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() + novaMovimentacao.getQuantidade());
        } else if (novaMovimentacao.getTipoMovimentacao() == TipoMovimentacao.SAIDA) {
            if (produto.getQuantidadeEmEstoque() < novaMovimentacao.getQuantidade()) {
                throw new EstoqueInsuficienteException(produto.getNome());
            }
            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - novaMovimentacao.getQuantidade());
        }

        produtoRepository.save(produto); // Salva o produto com estoque atualizado
        Movimentacao savedMovimentacao = movimentacaoRepository.save(novaMovimentacao); // Salva a movimentação
        return new MovimentacaoResponseDTO(savedMovimentacao); // Retorna o DTO de resposta
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

    // AQUI ESTÁ A CORREÇÃO!
    public List<MovimentacaoResponseDTO> findByProdutoId(Long produtoId) {
        // 1. Busca o Produto pelo ID. Se não encontrar, lança ProdutoNaoEncontradoException.
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));

        // 2. Agora, passa a ENTIDADE Produto para o método do repositório.
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

    // Corrigido para retornar List e não lançar exceção para lista vazia
    public List<MovimentacaoResponseDTO> findByTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        List<Movimentacao> movimentacoes = movimentacaoRepository.findByTipoMovimentacao(tipoMovimentacao);
        return movimentacoes.stream()
                .map(MovimentacaoResponseDTO::new)
                .toList();
    }

    // Removido o método update(Long id, Movimentacao movimentacao) duplicado/menos ideal

    @Transactional
    public MovimentacaoResponseDTO update(Long id, MovimentacaoRequestDTO requestDTO) {
        Movimentacao existingMovimentacao = movimentacaoRepository.findById(id)
                .orElseThrow(() -> new MovimentacaoNaoEncontradaException(id));

        // Lógica de atualização para campos não críticos ao estoque ou que não mudam a relação do produto.
        // CUIDADO: Se a quantidade ou tipo de movimentação mudar, a lógica de reajuste de estoque é complexa.
        // Para simplificar, estamos apenas atualizando os campos do DTO.
        // Se a regra de negócio permite alterar produto ou tipo/quantidade, considere estornar e criar nova.
        existingMovimentacao.setQuantidade(requestDTO.getQuantidade());
        existingMovimentacao.setTipoMovimentacao(requestDTO.getTipoMovimentacao());
        existingMovimentacao.setDataHora(requestDTO.getDataHora() != null ? requestDTO.getDataHora() : existingMovimentacao.getDataHora());

        // Verificação se o produto é o mesmo (não permite alterar o produto de uma movimentação existente)
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