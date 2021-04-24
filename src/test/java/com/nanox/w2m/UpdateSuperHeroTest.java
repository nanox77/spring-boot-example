package com.nanox.w2m;

import com.nanox.w2m.actions.UpdateSuperHero;
import com.nanox.w2m.domain.SuperHero;
import com.nanox.w2m.domain.SuperHeroRepository;
import com.nanox.w2m.infrastructure.InMemorySuperHeroRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class UpdateSuperHeroTest {

    private static final String NEW_SUPERMAN = "New Superman";
    private static final String SUPERMAN = "Superman";
    private static final String UNKNOWN_ID = "unknown_id";
    private static final String ID = "1";

    private SuperHeroRepository superHeroRepository;

    @Before
    public void setUp() {
        superHeroRepository = new InMemorySuperHeroRepository();
        superHeroRepository.add(new SuperHero(ID, SUPERMAN));
    }

    @Test
    public void givenSuperHeroesWhenUpdateNameThenReturnNewSuperHero() {
        UpdateSuperHero updateSuperHero = new UpdateSuperHero(superHeroRepository);

        updateSuperHero.execute(ID, NEW_SUPERMAN);

        Optional<SuperHero> superHeroOptional = superHeroRepository.getById(ID);
        Assert.assertTrue(superHeroOptional.isPresent());
        Assert.assertEquals(NEW_SUPERMAN, superHeroOptional.get().getName());
    }

    @Test
    public void givenSuperHeroesWhenUpdateNameWhichNotFoundThenSuperHeroIsNotUpdated() {
        UpdateSuperHero updateSuperHero = new UpdateSuperHero(superHeroRepository);

        updateSuperHero.execute(UNKNOWN_ID, NEW_SUPERMAN);

        Optional<SuperHero> superHeroOptional = superHeroRepository.getById(ID);
        Assert.assertTrue(superHeroOptional.isPresent());
        Assert.assertEquals(SUPERMAN, superHeroOptional.get().getName());
    }

}