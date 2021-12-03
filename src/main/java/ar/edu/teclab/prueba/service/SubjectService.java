package ar.edu.teclab.prueba.service;

import ar.edu.teclab.prueba.entity.Subject;

import java.util.List;

/**
 * @author Alexis
 */
public interface SubjectService {
    Subject findById(Long id);
    List<Subject> findAll();
    Subject create(Subject subject);
    Subject update(Subject subject, Long id);
    void delete(Long id);
}
