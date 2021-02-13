package com.abivan.graphics.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "GRAPHIC")
public class Graphic implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Long id;

    private String name;
    private Integer width;
    private Integer height;
    private Boolean rezise;
    @Lob
    @Column(name = "image")
    private byte[] image;

}
