package com.abivan.graphics.service.transformer;

import com.abivan.graphics.domain.Graphic;
import com.abivan.graphics.service.dto.GraphicDto;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class GraphicTransformer {

    public static Graphic getGraphicToImage(BufferedImage image, MultipartFile file) throws IOException {
        Graphic graphic = new Graphic();
        graphic.setImage(file.getBytes());
        graphic.setHeight(image.getHeight());
        graphic.setWidth(image.getWidth());
        graphic.setName(file.getOriginalFilename());
        return graphic;
    }

    public static GraphicDto getGraphicDtoToImage(Graphic graphic, String orientation) throws IOException {
        GraphicDto graphicDto = new GraphicDto();
        graphicDto.setHeight(graphic.getHeight());
        graphicDto.setWidth(graphic.getWidth());
        graphicDto.setOrientation(orientation);
        graphicDto.setImage(graphic.getImage());
        return graphicDto;
    }

}
