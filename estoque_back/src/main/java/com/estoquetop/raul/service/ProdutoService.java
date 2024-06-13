package com.estoquetop.raul.service;

import com.estoquetop.raul.dto.ProdutoDto;
import com.estoquetop.raul.exception.ResourceNotFoundException;
import com.estoquetop.raul.mapper.CustomModelMapper;
import com.estoquetop.raul.model.MarcaModel;
import com.estoquetop.raul.model.ProdutoModel;
import com.estoquetop.raul.repository.MarcaRepository;
import com.estoquetop.raul.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
private ProdutoRepository repository;
    @Autowired
    private MarcaRepository marcaRepository;

    public ProdutoDto findById(int id){
        ProdutoModel model = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não Encontrado!"));
        return CustomModelMapper.parseObject(model, ProdutoDto.class);
    }

    public Page<ProdutoDto> findAll(Pageable pageable){
        var page = repository.findAll(pageable);
        //using the map to iterate the objects and execute a method over them
        return page.map( marca -> CustomModelMapper.parseObject(marca, ProdutoDto.class));
    }

    public ProdutoDto create(ProdutoDto dto) {
        // Verifica se a marca associada ao produto existe
        MarcaModel marca = marcaRepository.findById(dto.getMarca().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Marca não encontrada!"));

        // Cria um novo modelo de produto
        ProdutoModel model = new ProdutoModel();
        model.setName(dto.getName());
        model.setMarca(marca);

        // Salva o produto no banco de dados
        ProdutoModel savedProduto = repository.save(model);

        // Retorna o DTO do produto salvo
        return CustomModelMapper.parseObject(savedProduto, ProdutoDto.class);
    }

    public ProdutoDto update(ProdutoDto dto){
        var produtoFound = repository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("Não Encontrado!"));
        var updated = CustomModelMapper.parseObject(dto, ProdutoModel.class);
        return CustomModelMapper.parseObject(repository.save(updated), ProdutoDto.class);
    }

    public void delete(int id){
        var produtoFound = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não Encontrado!"));
        repository.delete(produtoFound);
    }

    public Page<ProdutoDto> findByMarca(Pageable pageable, String marca){
        var page = repository.findByMarcaNameStartsWithIgnoreCase(pageable, marca);
        return page.map(produto -> CustomModelMapper.parseObject(produto, ProdutoDto.class));
    }



    public Page<ProdutoDto> findByName(Pageable pageable, String title){
        var page = repository.findByNameContainsIgnoreCase(pageable, title);
        return page.map(marca -> CustomModelMapper.parseObject(marca, ProdutoDto.class));
    }

}

