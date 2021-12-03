package ar.edu.teclab.prueba.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Alexis
 */
@Data
public class CareerRequestDTO {
    private String name;
    private String title;
    private List<Long> subjectIds;
}
