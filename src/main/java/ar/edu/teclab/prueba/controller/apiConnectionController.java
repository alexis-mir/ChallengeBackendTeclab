package ar.edu.teclab.prueba.controller;

import ar.edu.teclab.prueba.dto.CommentDto;
import ar.edu.teclab.prueba.dto.PageCommentsDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Map;

import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

/**
 * @author Alexis
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class apiConnectionController {

    private final WebClient client = WebClient.builder()
            .baseUrl("https://teclab1593636133.zendesk.com")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .filter(basicAuthentication("jorge.danni@teclab.edu.ar", "Abril2021*"))
            .build();
    private final ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/tickets/{id}/comments")
    public ResponseEntity<Object> listComments(@PathVariable Integer id) {


        Mono<Object> response = client.get()
                .uri("/api/v2/tickets/" + id.toString() + "/comments.json")
                .exchangeToMono(r -> {
                    if (r.statusCode()
                            .equals(HttpStatus.OK)) {
                        return r.bodyToMono(PageCommentsDto.class);
                    } else if (r.statusCode()
                            .is4xxClientError()) {
                        return Mono.just("Error response");
                    } else {
                        return r.createException()
                                .flatMap(Mono::error);
                    }
                });
        PageCommentsDto page = (PageCommentsDto) response.block();
        return new ResponseEntity<>(page != null ? page.getComments() : response.block(), HttpStatus.OK);
    }

    @PostMapping("/tickets/{id}/comments")
    public ResponseEntity<Object> createComments(@PathVariable Integer id, @RequestBody CommentDto commentDto) throws JsonProcessingException {
        Map<String, Object> map =
                Collections.singletonMap("ticket",
                        Collections.singletonMap("comment",
                                Collections.singletonMap("body", commentDto.getBody())));
        String comment = mapper.writeValueAsString(map);

        client.put()
                .uri("/api/v2/tickets/" + id.toString() + ".json")
                .bodyValue(comment)
                .exchangeToMono(r -> {
                    if (r.statusCode()
                            .equals(HttpStatus.OK)) {
                        return r.bodyToMono(Object.class);
                    } else if (r.statusCode()
                            .is4xxClientError()) {
                        return Mono.just("Error response");
                    } else {
                        return r.createException()
                                .flatMap(Mono::error);
                    }
                }).block();
        return new ResponseEntity<>(listComments(id), HttpStatus.OK);
    }
}
