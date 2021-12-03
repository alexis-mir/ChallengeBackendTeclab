package ar.edu.teclab.prueba.service.impl;

import ar.edu.teclab.prueba.entity.Career;
import ar.edu.teclab.prueba.exception.BadRequestException;
import ar.edu.teclab.prueba.exception.NotFoundException;
import ar.edu.teclab.prueba.repository.CareerRepository;
import ar.edu.teclab.prueba.service.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Alexis
 */
@Service
public class CareerServiceImpl implements CareerService {
    @Autowired
    private CareerRepository careerRepository;

    @Override
    public Career findById(Long id) {
        return careerRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Career id=" + id + " not found."));
    }

    @Override
    public List<Career> findAll() {
        return careerRepository.findAll();
    }

    @Override
    public Career create(Career career) {
        if (career.getId() != null && careerRepository.existsById(career.getId()))
            throw new BadRequestException("Career id=" + career.getId() + " already exists.");
        career.setCreateAt(LocalDateTime.now());
        return careerRepository.save(career);
    }

    @Override
    public Career update(Career careerUpdate, Long id) {
        Career career = findById(id);
        if (careerUpdate.getName() != null) career.setName(careerUpdate.getName());
        if (careerUpdate.getTitle() != null) career.setTitle(careerUpdate.getTitle());
        if (careerUpdate.getSubjects() != null) career.setSubjects(careerUpdate.getSubjects());
        return careerRepository.save(career);
    }

    @Override
    public void delete(Long id) {
        Career career = findById(id);
        career.setDeletedAt(LocalDateTime.now());
        careerRepository.deleteById(id);
    }
}
