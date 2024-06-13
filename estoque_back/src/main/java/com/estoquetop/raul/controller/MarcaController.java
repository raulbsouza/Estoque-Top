package com.estoquetop.raul.controller;

import com.estoquetop.raul.dto.MarcaDto;
import com.estoquetop.raul.exception.ResourceNotFoundException;
import com.estoquetop.raul.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marca")
public class MarcaController {

    @Autowired
    private MarcaService service;

    @PostMapping
    public MarcaDto create(@RequestBody MarcaDto dto){
        return service.create(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaDto> findById(@PathVariable("id") int id){
        try {
            MarcaDto marca = service.findById(id);
            return new ResponseEntity<>(marca, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<MarcaDto> findAll(){
        return service.findAll();
    }

    @PutMapping("/{id}")
    public MarcaDto update(@PathVariable("id") int id, @RequestBody MarcaDto dto){
        dto.setId(id);
        return service.update(dto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id){
        service.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }




}
