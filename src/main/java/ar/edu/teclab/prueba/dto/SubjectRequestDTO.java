package ar.edu.teclab.prueba.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Alexis
 */
@Data
public class SubjectRequestDTO {
    private String name;
    private List<Long> careerIds;
}
