package ml.danielcordero.tests.aplicacion.service.mapper;

import ml.danielcordero.tests.aplicacion.domain.*;
import ml.danielcordero.tests.aplicacion.service.dto.ConfiguracionVariableDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ConfiguracionVariable and its DTO ConfiguracionVariableDTO.
 */
@Mapper(componentModel = "spring", uses = {VariableMapper.class})
public interface ConfiguracionVariableMapper extends EntityMapper<ConfiguracionVariableDTO, ConfiguracionVariable> {

    @Mapping(source = "variable.id", target = "variableId")
    ConfiguracionVariableDTO toDto(ConfiguracionVariable configuracionVariable);

    @Mapping(source = "variableId", target = "variable")
    ConfiguracionVariable toEntity(ConfiguracionVariableDTO configuracionVariableDTO);

    default ConfiguracionVariable fromId(Long id) {
        if (id == null) {
            return null;
        }
        ConfiguracionVariable configuracionVariable = new ConfiguracionVariable();
        configuracionVariable.setId(id);
        return configuracionVariable;
    }
}
