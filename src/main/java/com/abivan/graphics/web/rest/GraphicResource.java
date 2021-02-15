package com.abivan.graphics.web.rest;

import com.abivan.graphics.service.GraphicService;
import com.abivan.graphics.service.dto.GraphicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/api")
@Validated
public class GraphicResource {

    @Autowired
    GraphicService graphicService;

    @PostMapping("/graphic")
    public ResponseEntity<GraphicDto> create(@RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(graphicService.save(file), HttpStatus.OK);
    }

    @GetMapping("/graphic/{id}")
    public ResponseEntity<GraphicDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(graphicService.findById(id), HttpStatus.OK);
    }

}
