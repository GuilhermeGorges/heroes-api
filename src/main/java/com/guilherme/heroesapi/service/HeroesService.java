package com.guilherme.heroesapi.service;

import com.guilherme.heroesapi.entity.Heroes;
import com.guilherme.heroesapi.repository.HeroesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class HeroesService {
    private final HeroesRepository heroesRepository;


    public Flux<Heroes> findAll(){
        return Flux.fromIterable(this.heroesRepository.findAll());
    }

    public Mono<Heroes> findByIdHero(String id){
        return Mono.justOrEmpty(this.heroesRepository.findById(id));
    }

    public Mono<Heroes> save(Heroes heroes){
        return Mono.justOrEmpty(this.heroesRepository.save(heroes));
    }

    public Mono<Boolean> deleteByIdHero(String id){
        heroesRepository.deleteById(id);
        return Mono.just(true);
    }
}
