package ar.edu.teclab.prueba.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.teclab.prueba.dto.Ejemplo;


@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "*")
public class PruebaController {

    private static final Log LOG = LogFactory.getLog(PruebaController.class);

    @GetMapping("/ejemplo")
    public ResponseEntity<Ejemplo> getMessageStatus(@RequestParam(value = "nombre") String nombre) {
        try {
            Ejemplo ejemplo = new Ejemplo();
            ejemplo.setNombre(nombre);
            return ResponseEntity.ok(ejemplo);
        } catch (Exception e) {
            LOG.error("Error", e);
        }
        return null;
    }
}


