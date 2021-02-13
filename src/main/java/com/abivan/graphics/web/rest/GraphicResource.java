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
import java.util.Objects;

@RestController
@RequestMapping("/api")
@Validated
public class GraphicResource {

    @Autowired
    GraphicService graphicService;

    @PostMapping("/graphic")
    public ResponseEntity<GraphicDto> create(@RequestParam("file") MultipartFile file) throws GraphicException, IOException {
        if(file.isEmpty()){
            throw new GraphicException("error", 400, "El archivo no esta presente");
        }
        if(!Objects.equals(file.getContentType(), "image/jpeg")){
            throw new GraphicException("formato", 400, "El archivo no tiene la extensión solicitada");
        }
        return new ResponseEntity<>(graphicService.save(file), HttpStatus.OK);
    }

    @GetMapping("/graphic/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
    return null;
    }

}
