package ar.edu.teclab.prueba.mapper;

import ar.edu.teclab.prueba.dto.SubjectRequestDTO;
import ar.edu.teclab.prueba.dto.SubjectResponseDTO;
import ar.edu.teclab.prueba.entity.Career;
import ar.edu.teclab.prueba.entity.Subject;
import ar.edu.teclab.prueba.service.CareerService;
import ar.edu.teclab.prueba.service.SubjectService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author Alexis
 */
@Mapper(componentModel = "spring", uses = { SubjectService.class, CareerService.class})
public interface SubjectMapper {
    @Mapping(target = "careers", source = "careerIds")
    Subject toEntity(SubjectRequestDTO dto);
    @Mapping(target = "careerIds", source = "careers")
    SubjectResponseDTO toDto(Subject subject);
    List<Career> map(List<Long> value);
    List<Long> toListIds(List<Career> careers);
    default Long toId(Career career) {
        return career.getId();
    }
}
