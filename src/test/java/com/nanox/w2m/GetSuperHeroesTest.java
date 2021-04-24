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

    public static final String ID_1 = "ID-1";
    public static final String ID_2 = "ID-2";
    private SuperHeroRepository superHeroRepository;

    @Before
    public void setUp() {
        superHeroRepository = new InMemorySuperHeroRepository();
    }

    @Test
    public void whenGetSuperHeroesThenReturnEmptyList() {
        GetSuperHeroes getSuperHeroes = new GetSuperHeroes(superHeroRepository);

        List<SuperHero> superHeroes = getSuperHeroes.execute();
        Assert.assertTrue(superHeroes.isEmpty());
    }

    @Test
    public void givenTwoSuperHeroesWhenGetSuperHeroesThenReturnAllSuperHeroes() {
        superHeroRepository.add(new SuperHero(ID_1, "Superman"));
        superHeroRepository.add(new SuperHero(ID_2, "Spiderman"));

        GetSuperHeroes getSuperHeroes = new GetSuperHeroes(superHeroRepository);

        List<SuperHero> superHeroes = getSuperHeroes.execute();
        Assert.assertEquals(2, superHeroes.size());
    }

}