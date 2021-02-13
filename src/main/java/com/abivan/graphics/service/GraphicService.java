package com.abivan.graphics.service;

import com.abivan.graphics.service.dto.GraphicDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface GraphicService {

    GraphicDto save(MultipartFile file) throws IOException;
}
