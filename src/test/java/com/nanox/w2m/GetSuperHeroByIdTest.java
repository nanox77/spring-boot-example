package com.nanox.w2m;

import com.nanox.w2m.actions.GetSuperHeroById;
import com.nanox.w2m.domain.SuperHero;
import com.nanox.w2m.domain.SuperHeroRepository;
import com.nanox.w2m.infrastructure.InMemorySuperHeroRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class GetSuperHeroByIdTest {

    public static final String ID_1 = "ID-1";
    public static final String ID_2 = "ID-2";
    public static final String ID_3 = "ID-3";
    private SuperHeroRepository superHeroRepository;

    @Before
    public void setUp() {
        superHeroRepository = new InMemorySuperHeroRepository();
        superHeroRepository.add(new SuperHero(ID_1));
        superHeroRepository.add(new SuperHero(ID_2));
    }

    @Test
    public void givenTwoSuperHeroesWhenGetSuperHeroByIdThenReturnUniqueSuperHero() {
        GetSuperHeroById getSuperHeroById = new GetSuperHeroById(superHeroRepository);
        Optional<SuperHero> superHeroOptional = getSuperHeroById.execute(ID_1);

        Assert.assertTrue(superHeroOptional.isPresent());
        Assert.assertEquals(ID_1, superHeroOptional.get().getId());
    }

    @Test
    public void givenTwoSuperHeroesWhenGetWithUnknownIdThenEmpty() {
        GetSuperHeroById getSuperHeroById = new GetSuperHeroById(superHeroRepository);
        Optional<SuperHero> superHeroOptional = getSuperHeroById.execute(ID_3);

        Assert.assertFalse(superHeroOptional.isPresent());
    }

}