package com.nanox.w2m.actions;

import com.nanox.w2m.domain.SuperHero;
import com.nanox.w2m.domain.SuperHeroRepository;

import java.util.Objects;
import java.util.Optional;

public class UpdateSuperHero {
    private final SuperHeroRepository superHeroRepository;

    public UpdateSuperHero(SuperHeroRepository superHeroRepository) {
        this.superHeroRepository = superHeroRepository;
    }

    public void execute(String superHeroId, String superHeroName) {
        Objects.requireNonNull(superHeroId, "Id is required");
        Objects.requireNonNull(superHeroName, "Name is required");

        Optional<SuperHero> superHeroOptional = this.superHeroRepository.getById(superHeroId);
        superHeroOptional.ifPresent(superHero -> {
            SuperHero newSuperHero = new SuperHero(superHeroId, superHeroName);
            this.superHeroRepository.add(newSuperHero);
        });
    }
}
