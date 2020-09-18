package com.beto.desafio.mapper;

import com.beto.desafio.dto.EpiDTO;
import com.beto.desafio.entities.Epi;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EpiMapper extends BaseMapper<Epi, EpiDTO>{
}
