package com.abivan.graphics.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import java.io.Serializable;

@Getter
@Setter
public class GraphicDto implements Serializable {

    private Integer width;
    private Integer height;
    private String orientation;
    @Lob
    private byte[] image;
}
