package com.abivan.graphics.service.imp;

import com.abivan.graphics.domain.Graphic;
import com.abivan.graphics.repository.GraphicRepository;
import com.abivan.graphics.service.GraphicService;
import com.abivan.graphics.service.dto.GraphicDto;
import com.abivan.graphics.service.exception.GraphicError;
import com.abivan.graphics.service.exception.GraphicException;
import com.abivan.graphics.service.transformer.GraphicTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GraphicServiceImp implements GraphicService {

    @Value("#{'${graphic.maxwidth}'}")
    private int maxWidth;

    @Value("#{'${graphic.maxheight}'}")
    private int maxHeight;

    @Autowired
    GraphicRepository graphicRepository;

    static final String PORTRAIT = "portrait";
    static final String LANDSCAPE = "landscape";

    @Override
    public GraphicDto save(MultipartFile file) throws IOException {
        if(file.isEmpty()){
            throw new GraphicException("El archivo no se encontró", new GraphicError(HttpStatus.BAD_REQUEST, "El campo file esta vacio"));
        }
        if(!Objects.equals(file.getContentType(), "image/jpeg")){
            throw new GraphicException("El archivo no tiene la extensión requerida", new GraphicError(HttpStatus.BAD_REQUEST,"El campo tiene una extensión invalida"));
        }
        BufferedImage image = convertToImage(file);
        String orientation = orientation(image.getHeight(), image.getWidth());

        if ((orientation.equals(PORTRAIT) && (image.getHeight() >= maxHeight || image.getWidth() >= maxWidth))
        || (orientation.equals(LANDSCAPE) && (image.getHeight() >= maxWidth || image.getWidth() >= maxHeight))) {
            List<Integer> newDimaensions = newDimension(image, orientation);
            image = resize(image, newDimaensions.get(0), newDimaensions.get(1));
        }
        return GraphicTransformer.getGraphicDtoToImage(graphicRepository.save(GraphicTransformer.getGraphicToImage(image, file, orientation)), orientation);
    }

    private BufferedImage convertToImage(MultipartFile file) throws IOException {
        InputStream in = new ByteArrayInputStream(file.getBytes());
        return ImageIO.read(in);
    }

    private static String orientation(int height, int width) {
        if (height < width) {
            return LANDSCAPE;
        }
        return PORTRAIT;
    }

    private List<Integer> newDimension(BufferedImage image, String orientation) {
        List<Integer> list = new ArrayList<>();
        int newWidth;
        int newHeight;
        float ratio = (float) image.getWidth() / image.getHeight();
        if (orientation.equals(PORTRAIT)) {
            if(image.getWidth()>maxWidth && image.getHeight()<maxHeight){
                newWidth = maxWidth;
                newHeight = calculeHeight(ratio, maxWidth);
            } else {
                newWidth = calculeWidth(ratio, maxHeight);
                newHeight = maxHeight;
            }
        } else {
            if(image.getWidth()>maxHeight && image.getHeight()<maxWidth){
                newWidth = calculeWidth(ratio, maxWidth);
                newHeight = maxWidth;
            } else {
                newWidth = maxHeight;
                newHeight = calculeHeight(ratio, maxHeight);
            }
        }
        list.add(newWidth);
        list.add(newHeight);
        return list;
    }

    private int calculeWidth(float ratio, int height) {
        return (int) (height * ratio);
    }

    private int calculeHeight(float ratio, int width) {
        return (int) (width / ratio);
    }

    private BufferedImage resize(BufferedImage image, int newWidth, int newHeight) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage newImage = new BufferedImage(newWidth, newHeight, image.getType());
        Graphics2D g = newImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(image, 0, 0, newWidth, newHeight, 0, 0, w, h, null);
        g.dispose();
        return newImage;
    }

    @Override
    public GraphicDto findById(Long id){
        Optional<Graphic> optionalGraphic = graphicRepository.findById(id);
        if(!optionalGraphic.isPresent()){
            throw new GraphicException("El Gráfico no se encontro", new GraphicError(HttpStatus.NOT_FOUND,"El gráfico no fue encontrado en la DB" ));
        }
        return GraphicTransformer.getGraphicDtoToGraphic(optionalGraphic.get());
    }
}
