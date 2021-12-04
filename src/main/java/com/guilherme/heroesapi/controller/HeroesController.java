package com.guilherme.heroesapi.controller;

import com.guilherme.heroesapi.entity.Heroes;
import com.guilherme.heroesapi.repository.HeroesRepository;
import com.guilherme.heroesapi.service.HeroesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.guilherme.heroesapi.constants.HeroesConstant.HEROES_ENDPOINT_LOCAL;

@RestController
@Slf4j
@AllArgsConstructor
public class HeroesController {

    public HeroesService heroesService;
    public HeroesRepository heroesRepository;

    @GetMapping(HEROES_ENDPOINT_LOCAL)
    public Flux<Heroes> getAllItems(){
            log.info("requesting the list off all heroes");
            return heroesService.findAll();
    }

    @GetMapping(HEROES_ENDPOINT_LOCAL+"/id")
    public Mono<ResponseEntity<Heroes>> findByHero(@PathVariable String id){
        log.info("requesting the list off all heroes");
        return heroesService.findByIdHero(id)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(code=HttpStatus.CREATED)
    public Mono<Heroes> createHero(@RequestBody Heroes heroes){
        log.info("a new hero was created");
        return heroesService.save(heroes);
    }

    @DeleteMapping(HEROES_ENDPOINT_LOCAL+"/id")
    @ResponseStatus(code =HttpStatus.CONTINUE)
    public Mono<HttpStatus> deleteByIDHero(@PathVariable String id){
        heroesService.deleteByIdHero(id);
        log.info("deleting a hero with id {}", id);
        return Mono.just(HttpStatus.CONTINUE);

    }




}
