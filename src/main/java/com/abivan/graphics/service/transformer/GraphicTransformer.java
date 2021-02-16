package com.abivan.graphics.service.transformer;

import com.abivan.graphics.domain.Graphic;
import com.abivan.graphics.service.dto.GraphicDto;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

public class GraphicTransformer implements Serializable {

    public static Graphic getGraphicToImage(BufferedImage image, MultipartFile file, String orientation) throws IOException {
        Graphic graphic = new Graphic();
        graphic.setImage(file.getBytes());
        graphic.setHeight(image.getHeight());
        graphic.setWidth(image.getWidth());
        graphic.setName(file.getOriginalFilename());
        graphic.setOrientation(orientation);
        graphic.setType(file.getContentType());
        return graphic;
    }

    public static GraphicDto getGraphicDtoToImage(Graphic graphic, String orientation) {
        GraphicDto graphicDto = new GraphicDto();
        graphicDto.setHeight(graphic.getHeight());
        graphicDto.setWidth(graphic.getWidth());
        graphicDto.setOrientation(orientation);
        graphicDto.setImage(graphic.getImage());
        graphicDto.setType(graphic.getType());
        return graphicDto;
    }

    public static GraphicDto getGraphicDtoToGraphic(Graphic graphic){
        GraphicDto graphicDto = new GraphicDto();
        graphicDto.setHeight(graphic.getHeight());
        graphicDto.setWidth(graphic.getWidth());
        graphicDto.setOrientation(graphic.getOrientation());
        graphicDto.setImage(graphic.getImage());
        graphicDto.setType(graphic.getType());
        return graphicDto;
    }

    @Override
    public String toString() {
        return "GraphicTransformer{}";
    }

}
