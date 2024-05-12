package com.lnreis.poi;

import com.lnreis.poi.entity.PoiEntity;
import com.lnreis.poi.repository.PoiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class PoiApplication implements CommandLineRunner {

    private final PoiRepository repository;
    public static void main(String[] args) {
        SpringApplication.run(PoiApplication.class, args);
    }

    @Override
    public void run(String... args){
        repository.save(new PoiEntity(null,"Lanchonete", 27L, 12L));
        repository.save(new PoiEntity(null,"Posto", 31L, 18L));
        repository.save(new PoiEntity(null,"Joalheria", 15L, 12L));
        repository.save(new PoiEntity(null,"Floricultura", 19L, 21L));
        repository.save(new PoiEntity(null,"Pub", 12L, 8L));
        repository.save(new PoiEntity(null,"Supermercado", 23L, 6L));
        repository.save(new PoiEntity(null,"Churrascaria", 28L, 2L));
    }
}
