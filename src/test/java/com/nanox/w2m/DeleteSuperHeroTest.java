package com.nanox.w2m;

import com.nanox.w2m.actions.DeleteSuperHero;
import com.nanox.w2m.domain.SuperHero;
import com.nanox.w2m.domain.SuperHeroRepository;
import com.nanox.w2m.infrastructure.InMemorySuperHeroRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class DeleteSuperHeroTest {

    private static final String ID_1 = "ID-1";
    private static final String ID_2 = "ID-2";
    private static final String ID_3 = "ID-3";
    private SuperHeroRepository superHeroRepository;

    @Before
    public void setUp() {
        superHeroRepository = new InMemorySuperHeroRepository();
        superHeroRepository.add(new SuperHero(ID_1, "Superman"));
        superHeroRepository.add(new SuperHero(ID_2, "Spiderman"));
    }

    @Test
    public void givenSuperHeroesWhenDeleteByIdThenSuperHeroIsDeleted() {
        DeleteSuperHero deleteSuperHero = new DeleteSuperHero(superHeroRepository);

        Optional<SuperHero> superHeroDeleted = deleteSuperHero.execute(ID_1);

        Assert.assertTrue(superHeroDeleted.isPresent());
        Assert.assertEquals(1, superHeroRepository.getAll().size());
    }


    @Test
    public void givenSuperHeroesWhenDeleteByUnknownIdThenEmpty() {
        DeleteSuperHero deleteSuperHero = new DeleteSuperHero(superHeroRepository);

        Optional<SuperHero> superHeroDeleted = deleteSuperHero.execute(ID_3);

        Assert.assertFalse(superHeroDeleted.isPresent());
        Assert.assertEquals(2, superHeroRepository.getAll().size());
    }

}