package ar.edu.teclab.prueba.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Alexis
 */
@Data
public class SubjectResponseDTO extends SubjectRequestDTO{
    private Long id;
    private LocalDateTime createAt;
}
