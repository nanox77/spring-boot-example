package com.nanox.w2m.actions;

import com.nanox.w2m.domain.SuperHero;
import com.nanox.w2m.domain.SuperHeroRepository;
import com.nanox.w2m.exceptions.InvalidInputException;

import java.util.Optional;

public class GetSuperHeroById {

    private final SuperHeroRepository superHeroRepository;

    public GetSuperHeroById(SuperHeroRepository superHeroRepository) {
        this.superHeroRepository = superHeroRepository;
    }

    public Optional<SuperHero> execute(String superHeroId) {
        if (superHeroId == null || superHeroId.isEmpty()) throw new InvalidInputException("Id is required");
        return superHeroRepository.getById(superHeroId);
    }
}
