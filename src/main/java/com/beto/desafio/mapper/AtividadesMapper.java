package com.beto.desafio.mapper;

import com.beto.desafio.dto.AtividadesDTO;
import com.beto.desafio.entities.Atividades;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AtividadesMapper extends BaseMapper<Atividades, AtividadesDTO>{
}
