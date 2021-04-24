package com.nanox.w2m;

import com.nanox.w2m.actions.GetSuperHeroByName;
import com.nanox.w2m.domain.SuperHero;
import com.nanox.w2m.domain.SuperHeroRepository;
import com.nanox.w2m.infrastructure.InMemorySuperHeroRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GetSuperHeroByNameTest {

    private SuperHeroRepository superHeroRepository;

    @Before
    public void setUp() {
        superHeroRepository = new InMemorySuperHeroRepository();
        superHeroRepository.add(new SuperHero("1", "Superman"));
        superHeroRepository.add(new SuperHero("2", "Thor"));
        superHeroRepository.add(new SuperHero("3", "Spiderman"));
        superHeroRepository.add(new SuperHero("4", "Manolito El Fuerte"));
    }

    @Test
    public void givenSuperHeroesWhenGetSuperHeroesByNameThenReturnSuperHeroesWhichContainsNameParam() {
        GetSuperHeroByName getSuperHeroByName = new GetSuperHeroByName(superHeroRepository);
        List<SuperHero> superHeroesByName = getSuperHeroByName.execute("man");

        Assert.assertEquals(3, superHeroesByName.size());
        Assert.assertEquals("1", findSuperHeroesById(superHeroesByName, "1").getId());
        Assert.assertEquals("3", findSuperHeroesById(superHeroesByName, "3").getId());
        Assert.assertEquals("4", findSuperHeroesById(superHeroesByName, "4").getId());
    }

    @Test
    public void givenSuperHeroesWhenGetSuperHeroesByNameWhichDoesNotMatchThenReturnEmpty() {
        GetSuperHeroByName getSuperHeroByName = new GetSuperHeroByName(superHeroRepository);
        List<SuperHero> superHeroesByName = getSuperHeroByName.execute("woman");

        Assert.assertTrue(superHeroesByName.isEmpty());
    }

    private SuperHero findSuperHeroesById(List<SuperHero> superHeroesByName, String superHeroesId) {
        return superHeroesByName.stream().filter(superHero -> superHero.getId().equals(superHeroesId)).findFirst().get();
    }

}