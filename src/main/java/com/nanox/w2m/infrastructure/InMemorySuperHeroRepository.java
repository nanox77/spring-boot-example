package com.nanox.w2m.infrastructure;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.nanox.w2m.domain.SuperHero;
import com.nanox.w2m.domain.SuperHeroRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemorySuperHeroRepository implements SuperHeroRepository {

    private final Map<String, SuperHero> superHeroMap = Maps.newConcurrentMap();

    @Override
    public void add(SuperHero superHero) {
        this.superHeroMap.put(superHero.getId(), superHero);
    }

    @Override
    public List<SuperHero> getAll() {
        return Lists.newArrayList(superHeroMap.values());
    }

    @Override
    public Optional<SuperHero> getById(String superHeroId) {
        return Optional.ofNullable(superHeroMap.get(superHeroId));
    }

    @Override
    public Optional<SuperHero> delete(String id) {
        return Optional.ofNullable(this.superHeroMap.remove(id));
    }
}