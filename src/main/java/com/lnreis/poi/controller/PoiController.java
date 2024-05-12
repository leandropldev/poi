package com.lnreis.poi.controller;

import com.lnreis.poi.entity.PoiEntity;
import com.lnreis.poi.repository.PoiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/poi")
@RequiredArgsConstructor
public class PoiController {

    private final PoiRepository repository;
    @GetMapping
    public ResponseEntity<Page<PoiEntity>> getAllPointsOfInterests(
        @RequestParam(name = "page", defaultValue = "0") Integer page,
        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ){
        Page<PoiEntity> all = repository.findAll(PageRequest.of(page, pageSize));
        return ResponseEntity.ok(all);
    }

    @GetMapping("/near-me")
    public ResponseEntity<List<PoiEntity>> getAllPointsOfInterestsNearMe(
            @RequestParam("x") Long x,
            @RequestParam("y") Long y,
            @RequestParam("dMax") Long dMax
    ){
        long xMin = x - dMax;
        long xMax = x + dMax;
        long yMin = y - dMax;
        long yMax = y + dMax;

        var nearMe = repository.findNearMe(xMin, xMax, yMin, yMax)
                .stream()
                .filter(e -> distanceBetweenTwoPoints(x, y, e.getX(), e.getY()) <= dMax)
                .toList();

        return ResponseEntity.ok(nearMe);
    }

    private Double distanceBetweenTwoPoints(Long x, Long y, Long eX, Long eY){
        return Math.sqrt(Math.pow(x - eX, 2) + Math.pow(y - eY, 2));
    }
}
