package com.nanox.w2m.infrastructure;

import com.google.common.collect.Lists;
import com.nanox.w2m.domain.SuperHero;
import com.nanox.w2m.domain.SuperHeroRepository;

import java.util.List;
import java.util.Optional;

public class H2SuperHeroRepository implements SuperHeroRepository {

    private final H2CrudRepository h2CrudRepository;

    public H2SuperHeroRepository(H2CrudRepository h2CrudRepository) {
        this.h2CrudRepository = h2CrudRepository;
    }

    @Override
    public void add(SuperHero superHero) {
        h2CrudRepository.save(superHero);
    }

    @Override
    public List<SuperHero> getAll() {
        return Lists.newArrayList(h2CrudRepository.findAll());
    }

    @Override
    public Optional<SuperHero> getById(String id) {
        return h2CrudRepository.findById(id);
    }

    @Override
    public void delete(SuperHero superHero) {
        h2CrudRepository.delete(superHero);
    }
}
