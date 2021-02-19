package com.abivan.graphics.resource;

import com.abivan.graphics.service.GraphicService;
import com.abivan.graphics.service.dto.GraphicDto;
import com.abivan.graphics.web.rest.GraphicResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.Lob;

public class GraphicControllerTest {

    private static final Long GRAPHIC_ID = 2L;
    private static final Integer HEIGHT = 1000;
    private static final Integer WIGTH = 800;
    private static final String ORIENTATION = "portrait";
    private static final String TYPE = "image/jpeg";

    public static final GraphicDto GRAPHIC_DTO = new GraphicDto();
    @Lob
    private byte[] image;

    @Mock
    GraphicService graphicService;

    @InjectMocks
    GraphicResource graphicResource;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        GRAPHIC_DTO.setHeight(HEIGHT);
        GRAPHIC_DTO.setWidth(WIGTH);
        GRAPHIC_DTO.setType(TYPE);
        GRAPHIC_DTO.setOrientation(ORIENTATION);
        GRAPHIC_DTO.setId(GRAPHIC_ID);

        Mockito.when(graphicService.findById(GRAPHIC_ID)).thenReturn(GRAPHIC_DTO);
    }

    @Test
    public void findByIdTest(){
        final ResponseEntity<GraphicDto> response = graphicResource.findById(GRAPHIC_ID);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody(), GRAPHIC_DTO);
    }
}
