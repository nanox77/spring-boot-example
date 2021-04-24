package com.nanox.w2m.actions;

import com.nanox.w2m.domain.SuperHero;
import com.nanox.w2m.domain.SuperHeroRepository;

import java.util.List;

public class GetSuperHeroes {

    private final SuperHeroRepository superHeroRepository;

    public GetSuperHeroes(SuperHeroRepository superHeroRepository) {
        this.superHeroRepository = superHeroRepository;
    }

    public List<SuperHero> execute() {
        return superHeroRepository.getAll();
    }
}