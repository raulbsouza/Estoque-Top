package com.estoquetop.raul.service;

import com.estoquetop.raul.dto.MarcaDto;
import com.estoquetop.raul.exception.ResourceNotFoundException;
import com.estoquetop.raul.mapper.CustomModelMapper;
import com.estoquetop.raul.model.MarcaModel;
import com.estoquetop.raul.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MarcaService {

    @Autowired
    private MarcaRepository repository;

    public MarcaDto findById(int id){
        MarcaModel model = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não encontrado!"));
        return CustomModelMapper.parseObject(model, MarcaDto.class);
    }

    public List<MarcaDto> findAll(){
        return CustomModelMapper.parseObjectList(repository.findAll(), MarcaDto.class);
    }

    public MarcaDto create(MarcaDto dto){
        MarcaModel model = CustomModelMapper.parseObject(dto, MarcaModel.class);
        return CustomModelMapper.parseObject(repository.save(model), MarcaDto.class);
    }

    public MarcaDto update(MarcaDto dto){
        var marcaFound = repository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("Não encontrado!"));
        var updated = CustomModelMapper.parseObject(dto, MarcaModel.class);
        return CustomModelMapper.parseObject(repository.save(updated), MarcaDto.class);
    }

    public void delete(int id){
        var marcaFound = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não encontrado!"));
        repository.delete(marcaFound);
    }


}
