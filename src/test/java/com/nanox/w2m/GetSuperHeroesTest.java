package com.nanox.w2m;

import com.nanox.w2m.actions.GetSuperHeroes;
import com.nanox.w2m.domain.SuperHero;
import com.nanox.w2m.domain.SuperHeroRepository;
import com.nanox.w2m.infrastructure.InMemorySuperHeroRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GetSuperHeroesTest {

    private SuperHeroRepository superHeroRepository;

    @Before
    public void setUp() {
        superHeroRepository = new InMemorySuperHeroRepository();
    }

    @Test
    public void whenGetHeroesThenReturnEmptyList() {
        GetSuperHeroes getSuperHeroes = new GetSuperHeroes(superHeroRepository);

        List<SuperHero> superHeroes = getSuperHeroes.execute();
        Assert.assertTrue(superHeroes.isEmpty());
    }

    @Test
    public void givenTwoHeroesWhenGetHeroesThenReturnAllHeroes() {
        superHeroRepository.add(new SuperHero());
        superHeroRepository.add(new SuperHero());

        GetSuperHeroes getSuperHeroes = new GetSuperHeroes(superHeroRepository);

        List<SuperHero> superHeroes = getSuperHeroes.execute();
        Assert.assertEquals(2, superHeroes.size());
    }

}