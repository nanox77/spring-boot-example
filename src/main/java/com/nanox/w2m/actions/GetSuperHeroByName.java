package com.nanox.w2m.actions;

import com.nanox.w2m.domain.SuperHero;
import com.nanox.w2m.domain.SuperHeroRepository;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GetSuperHeroByName {
    private final SuperHeroRepository superHeroRepository;

    public GetSuperHeroByName(SuperHeroRepository superHeroRepository) {
        this.superHeroRepository = superHeroRepository;
    }

    public List<SuperHero> execute(String name) {
        Predicate<SuperHero> findByName = superHero -> superHero.getName().toLowerCase().contains(name.toLowerCase());
        return this.superHeroRepository.getAll().stream().filter(findByName).collect(Collectors.toList());
    }
}