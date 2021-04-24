package com.nanox.w2m.domain;

import java.util.Objects;

public class SuperHero {

    private final String id;
    private final String name;

    public SuperHero(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
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