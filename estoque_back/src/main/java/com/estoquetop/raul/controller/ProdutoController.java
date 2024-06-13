package com.estoquetop.raul.controller;

import com.estoquetop.raul.dto.ProdutoDto;
import com.estoquetop.raul.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {@Autowired
private ProdutoService service;

    @GetMapping("/{id}")
    public ProdutoDto findById(@PathVariable("id") int id){
        ProdutoDto dto = service.findById(id);
        buildSelfLink(dto);
        return dto;
    }

    @GetMapping
    public ResponseEntity<PagedModel<ProdutoDto>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            PagedResourcesAssembler<ProdutoDto> assembler
    ){
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));
        Page<ProdutoDto> produtos = service.findAll(pageable);
        for(ProdutoDto produto : produtos){
            buildSelfLink(produto);
        }
        return new ResponseEntity(assembler.toModel(produtos), HttpStatus.OK);
    }

    @GetMapping("/find/marca/")
    public ResponseEntity<PagedModel<ProdutoDto>> findByAuthor(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestParam(value = "direction", required = false, defaultValue = "desc") String direction,
            @RequestParam(value = "marca", required = true) String marca,
            PagedResourcesAssembler<ProdutoDto> assembler
    ){
        var sortDiretion = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDiretion, "title" ));
        Page<ProdutoDto> produtos = service.findByMarca(pageable, marca);
        for(ProdutoDto produto : produtos){
            buildSelfLink(produto);
        }
        return new ResponseEntity(assembler.toModel(produtos), HttpStatus.OK);
    }

    @GetMapping("/find/name/")
    public ResponseEntity<PagedModel<ProdutoDto>> findByTitle(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestParam(value = "direction", required = false, defaultValue = "desc") String direction,
            @RequestParam(value = "marca", required = true) String marca,
            PagedResourcesAssembler<ProdutoDto> assembler
    ){
        var sortDiretion = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDiretion, "title" ));
        Page<ProdutoDto> produtos = service.findByName(pageable, marca);
        for(ProdutoDto produto : produtos){
            buildSelfLink(produto);
        }
        return new ResponseEntity(assembler.toModel(produtos), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> create(@RequestBody ProdutoDto dto) {
        try {
            ProdutoDto createdProduto = service.create(dto);
            return ResponseEntity.ok(createdProduto);
        } catch (Exception e) {
            // Log do erro para debug
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @PutMapping("/{id}")
    public ProdutoDto update(@PathVariable("id") int id, @RequestBody ProdutoDto dto){
        dto.setId(id);
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id){
        service.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    private void buildSelfLink(ProdutoDto dto){
        //..link to self
        dto.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(
                                this.getClass()
                        ).findById(dto.getId())
                ).withSelfRel()
        );
        dto.getMarca().add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(MarcaController.class)
                                .findById(dto.getMarca().getId())
                ).withSelfRel()
        );

    }




}

