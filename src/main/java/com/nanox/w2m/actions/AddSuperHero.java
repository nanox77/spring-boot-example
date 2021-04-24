package com.nanox.w2m.actions;

import com.nanox.w2m.domain.SuperHero;
import com.nanox.w2m.domain.SuperHeroRepository;

import java.util.UUID;

public class AddSuperHero {
    private final SuperHeroRepository superHeroRepository;

    public AddSuperHero(SuperHeroRepository superHeroRepository) {
        this.superHeroRepository = superHeroRepository;
    }

    public SuperHero execute(String name) {
        SuperHero superHero = new SuperHero(UUID.randomUUID().toString(), name);
        this.superHeroRepository.add(superHero);
        return superHero;
    }
}