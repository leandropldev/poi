package com.lnreis.poi.repository;

import com.lnreis.poi.entity.PoiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PoiRepository extends JpaRepository<PoiEntity, Long> {

    @Query("""
        SELECT p
        FROM PoiEntity p
        WHERE (p.x >= :xMin AND p.x <= :xMax AND p.y >= :yMin AND p.y <= :yMax)
    """)
    List<PoiEntity> findNearMe(
        @Param("xMin") long xMin,
        @Param("xMax") long xMax,
        @Param("yMin") long yMin,
        @Param("yMax") long yMax
    );
}
