package com.beto.desafio.mapper;

import com.beto.desafio.dto.CargosDTO;
import com.beto.desafio.entities.Cargos;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CargosMapper extends BaseMapper<Cargos, CargosDTO>{
}
