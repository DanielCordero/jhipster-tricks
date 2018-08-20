package ml.danielcordero.tests.aplicacion.service.mapper;

import ml.danielcordero.tests.aplicacion.domain.*;
import ml.danielcordero.tests.aplicacion.service.dto.VariableDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Variable and its DTO VariableDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface VariableMapper extends EntityMapper<VariableDTO, Variable> {


    @Mapping(target = "configuracionVariables", ignore = true)
    Variable toEntity(VariableDTO variableDTO);

    default Variable fromId(Long id) {
        if (id == null) {
            return null;
        }
        Variable variable = new Variable();
        variable.setId(id);
        return variable;
    }
}
