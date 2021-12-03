package ar.edu.teclab.prueba.mapper;

import ar.edu.teclab.prueba.dto.CareerRequestDTO;
import ar.edu.teclab.prueba.dto.CareerResponseDTO;
import ar.edu.teclab.prueba.dto.SubjectResponseDTO;
import ar.edu.teclab.prueba.entity.Career;
import ar.edu.teclab.prueba.entity.Subject;
import ar.edu.teclab.prueba.service.CareerService;
import ar.edu.teclab.prueba.service.SubjectService;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author Alexis
 */
@Mapper(componentModel = "spring", uses = { CareerService.class, SubjectService.class})
public interface CareerMapper {
    @Mapping(target = "subjectIds", source = "subjects")
    CareerResponseDTO toDto(Career career);
    @Mapping(target = "subjects", source = "subjectIds")
    Career toEntity(CareerRequestDTO dto);
    List<Subject> map(List<Long> ids);
    List<Long> toListIds(List<Subject> subjects);
    default Long toId(Subject subject) {
        return subject.getId();
    }
}
