package com.estoquetop.raul.repository;

import com.estoquetop.raul.model.MarcaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<MarcaModel, Integer> {


}
