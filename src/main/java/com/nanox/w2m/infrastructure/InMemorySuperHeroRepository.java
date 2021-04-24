package com.nanox.w2m.infrastructure;

import com.google.common.collect.Lists;
import com.nanox.w2m.domain.SuperHero;
import com.nanox.w2m.domain.SuperHeroRepository;

import java.util.Collections;
import java.util.List;

public class InMemorySuperHeroRepository implements SuperHeroRepository {

    private final List<SuperHero> superHeroes = Lists.newArrayList();

    @Override
    public void add(SuperHero superHero) {
        this.superHeroes.add(superHero);
    }

    @Override
    public List<SuperHero> getAll() {
        return Collections.unmodifiableList(superHeroes);
    }
}