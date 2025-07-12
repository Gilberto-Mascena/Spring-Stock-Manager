package br.com.mascenadev.springstockmanager.service;

import br.com.mascenadev.springstockmanager.exception.CnpjJaCadastradoException;
import br.com.mascenadev.springstockmanager.exception.FornecedorNaoEncontradoException;
import br.com.mascenadev.springstockmanager.model.fornecedor.Fornecedor;
import br.com.mascenadev.springstockmanager.model.fornecedor.dto.FornecedorRequestDTO;
import br.com.mascenadev.springstockmanager.model.fornecedor.dto.FornecedorResponseDTO;
import br.com.mascenadev.springstockmanager.repository.FornecedorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    @Transactional
    public FornecedorResponseDTO save(FornecedorRequestDTO fornecedorRequestDTO) {

        if (fornecedorRepository.findByCnpj(fornecedorRequestDTO.getCnpj()).isPresent()) {
            throw new CnpjJaCadastradoException(fornecedorRequestDTO.getCnpj());
        }
        Fornecedor fornecedor = new Fornecedor();
        BeanUtils.copyProperties(fornecedorRequestDTO, fornecedor);
        Fornecedor fornecedorSalvo = fornecedorRepository.save(fornecedor);
        return new FornecedorResponseDTO(fornecedorSalvo);
    }

    public List<FornecedorResponseDTO> findAll() {
        List<Fornecedor> fornecedores = fornecedorRepository.findAll();
        return fornecedores.stream()
                .map(FornecedorResponseDTO::new)
                .toList();
    }

    public FornecedorResponseDTO findById(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new FornecedorNaoEncontradoException(id));
        return new FornecedorResponseDTO(fornecedor);
    }

    @Transactional
    public FornecedorResponseDTO update(Long id, FornecedorRequestDTO fornecedorRequestDTO) {
        fornecedorRepository.findByCnpj(fornecedorRequestDTO.getCnpj())
                .ifPresent(f -> {
                    if (!f.getId().equals(id)) { // Se o CNPJ encontrado pertence a outro ID
                        throw new CnpjJaCadastradoException(fornecedorRequestDTO.getCnpj());
                    }
                });
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new FornecedorNaoEncontradoException(id));

        BeanUtils.copyProperties(fornecedorRequestDTO, fornecedor, "id");
        Fornecedor fornecedorAtualizado = fornecedorRepository.save(fornecedor);
        return new FornecedorResponseDTO(fornecedorAtualizado);
    }

    @Transactional
    public void delete(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new FornecedorNaoEncontradoException(id));
        fornecedorRepository.delete(fornecedor);
    }
}
