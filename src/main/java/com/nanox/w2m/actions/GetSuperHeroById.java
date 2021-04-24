package com.nanox.w2m.actions;

import com.nanox.w2m.domain.SuperHero;
import com.nanox.w2m.domain.SuperHeroRepository;

import java.util.Objects;
import java.util.Optional;

public class GetSuperHeroById {

    private final SuperHeroRepository superHeroRepository;

    public GetSuperHeroById(SuperHeroRepository superHeroRepository) {
        this.superHeroRepository = superHeroRepository;
    }

    public Optional<SuperHero> execute(String superHeroId) {
        Objects.requireNonNull(superHeroId, "Id is required");
        return superHeroRepository.getAll().stream().filter(superHero -> superHero.getId().equals(superHeroId)).findFirst();
    }
}
