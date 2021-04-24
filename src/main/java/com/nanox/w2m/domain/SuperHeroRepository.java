package com.nanox.w2m.domain;

import java.util.List;

public interface SuperHeroRepository {

    void add(SuperHero superHero);

    List<SuperHero> getAll();

}