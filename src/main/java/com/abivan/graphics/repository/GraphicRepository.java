package com.abivan.graphics.repository;

import com.abivan.graphics.domain.Graphic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraphicRepository extends JpaRepository<Graphic, Integer> {
}
