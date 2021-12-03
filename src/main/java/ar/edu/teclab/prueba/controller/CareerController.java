package ar.edu.teclab.prueba.controller;

import ar.edu.teclab.prueba.dto.CareerRequestDTO;
import ar.edu.teclab.prueba.dto.CareerResponseDTO;
import ar.edu.teclab.prueba.dto.SubjectRequestDTO;
import ar.edu.teclab.prueba.mapper.CareerMapper;
import ar.edu.teclab.prueba.service.CareerService;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/careers")
@Slf4j
public class CareerController {
    @Autowired
    private CareerService careerService;

    @Autowired
    private CareerMapper careerMapper;

    @GetMapping(path = "/{id}")
    public ResponseEntity<CareerResponseDTO> findById (@PathVariable Long id){
        return new ResponseEntity<>(
                careerMapper.toDto(careerService.findById(id)),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<List<CareerResponseDTO>> findAll (){
        return new ResponseEntity<>(
                careerService.findAll().stream()
                        .map(careerMapper::toDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<CareerResponseDTO> create (@RequestBody CareerRequestDTO requestDTO){
        return new ResponseEntity<>(
                careerMapper.toDto(
                        careerService.create(
                                careerMapper.toEntity(requestDTO)
                        )),
                HttpStatus.CREATED
        );
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CareerResponseDTO> update (@RequestBody CareerRequestDTO requestDTO, @PathVariable Long id){
        log.info(careerMapper.toEntity(requestDTO).toString());
        return new ResponseEntity<>(
                careerMapper.toDto(
                        careerService.update(
                                careerMapper.toEntity(requestDTO), id
                        )),
                HttpStatus.OK
        );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CareerResponseDTO> delete (@PathVariable Long id){
        careerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
