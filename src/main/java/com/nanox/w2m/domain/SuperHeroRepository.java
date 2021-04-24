package com.nanox.w2m.domain;

import java.util.List;
import java.util.Optional;

public interface SuperHeroRepository {

    void add(SuperHero superHero);

    List<SuperHero> getAll();

    Optional<SuperHero> getById(String id);

    void delete(SuperHero superHero);
}