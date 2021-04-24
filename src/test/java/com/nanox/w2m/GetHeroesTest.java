package com.nanox.w2m;

import com.nanox.w2m.actions.GetHeroes;
import com.nanox.w2m.domain.Heroes;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class GetHeroesTest {

    @Test
    public void whenGetHeroesThenReturnEmptyList() {
        GetHeroes getHeroes = new GetHeroes();

        List<Heroes> heroes = getHeroes.execute();
        Assert.assertTrue(heroes.isEmpty());
    }

}