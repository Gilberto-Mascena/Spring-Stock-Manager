package br.com.mascenadev.springstockmanager.service;

import br.com.mascenadev.springstockmanager.model.Fornecedor;
import br.com.mascenadev.springstockmanager.model.produto.Produto;
import br.com.mascenadev.springstockmanager.model.produto.dto.ProdutoRequestDTO;
import br.com.mascenadev.springstockmanager.model.produto.dto.ProdutoResponseDTO;
import br.com.mascenadev.springstockmanager.repository.FornecedorRepository;
import br.com.mascenadev.springstockmanager.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;
    private final FornecedorRepository fornecedorRepository;

    public ProdutoService(ProdutoRepository repository, FornecedorRepository fornecedorRepository) {
        this.repository = repository;
        this.fornecedorRepository = fornecedorRepository;
    }

    @Transactional
    public ProdutoResponseDTO save(ProdutoRequestDTO produtoRequestDTO) {

        Produto produto = new Produto();
        BeanUtils.copyProperties(produtoRequestDTO, produto, "fornecedorId");
        Fornecedor fornecedor = fornecedorRepository.findById(produtoRequestDTO.getFornecedorId())
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado" + produtoRequestDTO.getFornecedorId()));
        produto.setFornecedor(fornecedor);
        Produto produtoSalvo = repository.save(produto);
        return new ProdutoResponseDTO(produtoSalvo);
    }

    public List<ProdutoResponseDTO> findAll() {
        List<Produto> produtos = repository.findAll();
        return produtos.stream()
                .map(ProdutoResponseDTO::new)
                .toList();
    }

    public ProdutoResponseDTO findById(Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return new ProdutoResponseDTO(produto);
    }

    @Transactional
    public ProdutoResponseDTO update(Long id, ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setNome(produtoRequestDTO.getNome());
        produto.setDescricao(produtoRequestDTO.getDescricao());
        produto.setPrecoUnitario(produtoRequestDTO.getPrecoUnitario());
        produto.setQuantidadeEmEstoque(produtoRequestDTO.getQuantidadeEmEstoque());

        if (!produto.getFornecedor().getId().equals(produtoRequestDTO.getFornecedorId())) { // Verifica se o fornecedor mudou
            Fornecedor novoFornecedor = fornecedorRepository.findById(produtoRequestDTO.getFornecedorId())
                    .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado para o ID: " + produtoRequestDTO.getFornecedorId()));
            produto.setFornecedor(novoFornecedor);

        }

        Produto produtoAtualizado = repository.save(produto);
        return new ProdutoResponseDTO(produtoAtualizado);
    }

    @Transactional
    public void delete(Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado para o ID: " + id)); // Mensagem mais específica
        repository.delete(produto);
    }
}
