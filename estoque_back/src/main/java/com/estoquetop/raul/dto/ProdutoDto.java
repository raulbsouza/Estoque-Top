package com.estoquetop.raul.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Relation(itemRelation = "produto", collectionRelation = "produtos")
public class ProdutoDto extends RepresentationModel<ProdutoDto> {

    private int id;

    private String name;

    private MarcaDto marca;
}
