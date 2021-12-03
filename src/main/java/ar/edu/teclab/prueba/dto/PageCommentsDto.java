package ar.edu.teclab.prueba.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Alexis
 */
@Data
public class PageCommentsDto {
    private List<Object> comments;
    private String next_page;
    private String previous_page;
    private Integer count;
}
