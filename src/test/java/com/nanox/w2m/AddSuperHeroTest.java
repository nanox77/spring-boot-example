package com.nanox.w2m;

import com.nanox.w2m.actions.AddSuperHero;
import com.nanox.w2m.domain.SuperHero;
import com.nanox.w2m.domain.SuperHeroRepository;
import com.nanox.w2m.infrastructure.InMemorySuperHeroRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddSuperHeroTest {

    private static final String NAME = "Superman";
    private SuperHeroRepository superHeroRepository;

    @Before
    public void setUp() {
        superHeroRepository = new InMemorySuperHeroRepository();
    }

    @Test
    public void givenValidNameWhenAddSuperHeroThenCreateSuperHero() {
        AddSuperHero addSuperHero = new AddSuperHero(superHeroRepository);

        SuperHero superHero = addSuperHero.execute(NAME);

        Assert.assertNotNull(superHero.getId());
        Assert.assertEquals(NAME, superHero.getName());
        Assert.assertEquals(1, superHeroRepository.getAll().size());
    }

}