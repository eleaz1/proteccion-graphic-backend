package com.abivan.graphics.service;

import com.abivan.graphics.domain.Graphic;
import com.abivan.graphics.repository.GraphicRepository;
import com.abivan.graphics.service.exception.GraphicException;
import com.abivan.graphics.service.imp.GraphicServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class GraphicServiceTest {

    private static final Long GRAPHIC_ID = 2L;
    private static final Integer HEIGHT = 1000;
    private static final Integer WIGTH = 800;
    private static final String ORIENTATION = "portrait";
    private static final String TYPE = "image/jpeg";

    public static final Graphic GRAPHIC = new Graphic();

    @Mock
    GraphicRepository graphicRepository;

    @InjectMocks
    GraphicServiceImp graphicService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        GRAPHIC.setHeight(HEIGHT);
        GRAPHIC.setWidth(WIGTH);
        GRAPHIC.setType(TYPE);
        GRAPHIC.setOrientation(ORIENTATION);
        GRAPHIC.setId(GRAPHIC_ID);
    }

    @Test
    public void findByIdTest() {
        Mockito.when(graphicRepository.findById(GRAPHIC_ID)).thenReturn(Optional.of(GRAPHIC));
        graphicService.findById(GRAPHIC_ID);
    }

    @Test(expected = GraphicException.class)
    public void findByIdTestError() {
        Mockito.when(graphicRepository.findById(GRAPHIC_ID)).thenReturn(Optional.empty());
        graphicService.findById(GRAPHIC_ID);
    }

}
