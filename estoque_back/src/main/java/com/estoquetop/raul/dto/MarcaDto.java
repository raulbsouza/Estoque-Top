package com.estoquetop.raul.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MarcaDto extends RepresentationModel<MarcaDto> {
    private int id;
    private String name;
    private String type;
}
