package com.estoquetop.raul.repository;

import com.estoquetop.raul.model.ProdutoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Integer> {

    public Page<ProdutoModel> findAll(Pageable pageable);

    public Page<ProdutoModel> findByMarcaNameStartsWithIgnoreCase(Pageable pageable, String marca);

    public Page<ProdutoModel> findByNameContainsIgnoreCase(Pageable pageable, String name);
}