package com.abivan.graphics.service;

import com.abivan.graphics.service.dto.GraphicDto;
import com.abivan.graphics.service.errors.GraphicException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface GraphicService {

    GraphicDto save(MultipartFile file) throws IOException;
    Optional<GraphicDto> findById(Long id) throws GraphicException;
}
