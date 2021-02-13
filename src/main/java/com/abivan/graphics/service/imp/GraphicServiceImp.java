package com.abivan.graphics.service.imp;

import com.abivan.graphics.service.GraphicService;
import com.abivan.graphics.service.dto.GraphicDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class GraphicServiceImp implements GraphicService{
    @Override
    public GraphicDto save(MultipartFile file) throws IOException{
        BufferedImage image = convertToImage(file);

        GraphicDto graphicDto = new GraphicDto();
        graphicDto.setHeight(image.getHeight());
        graphicDto.setWidth(image.getWidth());
        graphicDto.setOrientation(orientation(graphicDto.getHeight(), graphicDto.getWidth()));
        return graphicDto;
    }

    private BufferedImage convertToImage(MultipartFile file) throws IOException {
        InputStream in = new ByteArrayInputStream(file.getBytes());
        return ImageIO.read(in);
    }

    public String orientation(int height, int width){
        if(height<width){
            return "landscape";
        }
        return "portrait";
    }
}
