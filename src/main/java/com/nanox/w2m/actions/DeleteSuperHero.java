package com.nanox.w2m.actions;

import com.nanox.w2m.domain.SuperHeroRepository;

public class DeleteSuperHero {

    private final SuperHeroRepository superHeroRepository;

    public DeleteSuperHero(SuperHeroRepository superHeroRepository) {
        this.superHeroRepository = superHeroRepository;
    }


    public void execute(String superHeroId) {
        superHeroRepository.getById(superHeroId).ifPresent(superHeroRepository::delete);
    }
}