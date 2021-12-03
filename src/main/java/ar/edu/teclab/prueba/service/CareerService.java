package ar.edu.teclab.prueba.service;

import ar.edu.teclab.prueba.entity.Career;

import java.util.List;

/**
 * @author Alexis
 */
public interface CareerService {
    Career findById(Long id);
    List<Career> findAll();
    Career create(Career career);
    Career update(Career career, Long id);
    void delete(Long id);
}
