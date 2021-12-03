package com.guilherme.heroesapi.repository;

import com.guilherme.heroesapi.entity.Heroes;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface HeroesRepository extends CrudRepository<Heroes, String> {

}
