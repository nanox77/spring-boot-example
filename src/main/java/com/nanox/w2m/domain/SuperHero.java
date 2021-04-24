package com.nanox.w2m.domain;

import java.util.Objects;

public class SuperHero {

    private final String id;

    public SuperHero(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuperHero superHero = (SuperHero) o;
        return Objects.equals(id, superHero.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}