package com.abivan.graphics.resource;

import com.abivan.graphics.service.GraphicService;
import com.abivan.graphics.web.rest.GraphicResource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class GraphicControllerTest {

    @Mock
    GraphicService graphicService;

    @InjectMocks
    GraphicResource graphicResource;
}
