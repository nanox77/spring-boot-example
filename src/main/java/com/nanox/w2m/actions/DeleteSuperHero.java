package com.nanox.w2m.actions;

import com.nanox.w2m.domain.SuperHero;
import com.nanox.w2m.domain.SuperHeroRepository;

import java.util.Optional;

public class DeleteSuperHero {

    private final SuperHeroRepository superHeroRepository;

    public DeleteSuperHero(SuperHeroRepository superHeroRepository) {
        this.superHeroRepository = superHeroRepository;
    }


    public Optional<SuperHero> execute(String superHeroId) {
        return superHeroRepository.delete(superHeroId);
    }
}