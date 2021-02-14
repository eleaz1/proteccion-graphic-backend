package com.abivan.graphics.web.rest;

import com.abivan.graphics.service.GraphicService;
import com.abivan.graphics.service.dto.GraphicDto;
import com.abivan.graphics.service.errors.GraphicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Validated
public class GraphicResource {

    @Autowired
    GraphicService graphicService;

    @PostMapping("/graphic")
    public ResponseEntity<Map<String, Object>> create(@RequestParam("file") MultipartFile file) throws IOException {
        Map<String, Object> response = new HashMap<>();
        if(file.isEmpty()){
            response.put("menssaje", "El archivo no esta presente");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if(!Objects.equals(file.getContentType(), "image/jpeg")){
            response.put("menssaje", "El archivo no tiene la extensión solicitada");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        response.put("object",graphicService.save(file));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/graphic/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) throws GraphicException {
        Map<String, Object> response = new HashMap<>();
        Optional<GraphicDto> graphicDtoOptional = graphicService.findById(id);
        if (!graphicDtoOptional.isPresent()){
            throw new GraphicException(404, "El Gráfico no se encontro");
        }
        response.put("object", graphicDtoOptional.get());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
