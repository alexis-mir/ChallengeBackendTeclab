package ar.edu.teclab.prueba.controller;

import ar.edu.teclab.prueba.dto.SubjectRequestDTO;
import ar.edu.teclab.prueba.dto.SubjectResponseDTO;
import ar.edu.teclab.prueba.mapper.SubjectMapper;
import ar.edu.teclab.prueba.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexis
 */
@RestController
@RequestMapping("/subjects")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @Autowired
    SubjectMapper subjectMapper;

    @GetMapping(path = "/{id}")
    public ResponseEntity<SubjectResponseDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(
                subjectMapper.toDto(subjectService.findById(id)),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<List<SubjectResponseDTO>> findAll() {
        return new ResponseEntity<>(
                subjectService.findAll().stream()
                        .map(subjectMapper::toDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<SubjectResponseDTO> create(@RequestBody SubjectRequestDTO requestDTO) {
        return new ResponseEntity<>(
                subjectMapper.toDto(
                        subjectService.create(
                                subjectMapper.toEntity(requestDTO)
                        )),
                HttpStatus.CREATED
        );
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SubjectResponseDTO> update(@RequestBody SubjectRequestDTO requestDTO, @PathVariable Long id) {
        return new ResponseEntity<>(
                subjectMapper.toDto(
                        subjectService.update(
                                subjectMapper.toEntity(requestDTO), id
                        )),
                HttpStatus.OK
        );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        subjectService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
