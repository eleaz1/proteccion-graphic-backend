package com.abivan.graphics.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "GRAPHIC")
public class Graphic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private int id;

    private String name;
    private Integer width;
    private Integer length;
    private Boolean rezise;
    @Lob
    @Column(name = "image")
    private byte[] image;

}
