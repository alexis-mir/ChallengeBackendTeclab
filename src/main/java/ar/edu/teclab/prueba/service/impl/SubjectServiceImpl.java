package ar.edu.teclab.prueba.service.impl;

import ar.edu.teclab.prueba.entity.Subject;
import ar.edu.teclab.prueba.exception.BadRequestException;
import ar.edu.teclab.prueba.exception.NotFoundException;
import ar.edu.teclab.prueba.repository.SubjectRepository;
import ar.edu.teclab.prueba.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Alexis
 */
@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Subject findById(Long id) {
        return subjectRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Subject id=" + id + " not found."));
    }

    @Override
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject create(Subject subject) {
        if (subject.getId() != null && subjectRepository.existsById(subject.getId()))
            throw new BadRequestException("Subject id=" + subject.getId() + " already exists.");
        subject.setCreateAt(LocalDateTime.now());
        return subjectRepository.save(subject);
    }

    @Override
    public Subject update(Subject subjectUpdate, Long id) {
        Subject subject = findById(id);
        if (subjectUpdate.getName() != null) subject.setName(subjectUpdate.getName());
        if (subjectUpdate.getCareers() != null) subject.setCareers(subjectUpdate.getCareers());
        return subjectRepository.save(subject);
    }

    @Override
    public void delete(Long id) {
        Subject subject = findById(id);
        subject.setDeletedAt(LocalDateTime.now());
        subjectRepository.deleteById(id);
    }
}
