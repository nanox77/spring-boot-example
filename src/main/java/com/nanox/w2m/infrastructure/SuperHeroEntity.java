package com.nanox.w2m.infrastructure;


import com.nanox.w2m.domain.SuperHero;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "SUPERHEROES")
public class SuperHeroEntity implements Serializable {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    public static SuperHeroEntity from(SuperHero superHero) {
        SuperHeroEntity superHeroEntity = new SuperHeroEntity();
        superHeroEntity.setId(superHero.getId());
        superHeroEntity.setName(superHero.getName());
        return superHeroEntity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}