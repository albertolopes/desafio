package com.beto.desafio.mapper;

import com.beto.desafio.dto.FuncionarioDTO;
import com.beto.desafio.entities.Funcionario;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FuncionarioMapper extends BaseMapper<Funcionario, FuncionarioDTO> {
}
