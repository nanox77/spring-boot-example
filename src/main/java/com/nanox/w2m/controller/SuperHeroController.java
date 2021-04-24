package com.nanox.w2m.controller;

import com.nanox.w2m.actions.*;
import com.nanox.w2m.controller.requests.UpdateSuperHeroRequest;
import com.nanox.w2m.domain.SuperHero;
import com.nanox.w2m.domain.SuperHeroRepository;
import com.nanox.w2m.infrastructure.InMemorySuperHeroRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
public class SuperHeroController {

    private final GetSuperHeroes getSuperHeroes;
    private final GetSuperHeroById getSuperHeroById;
    private final GetSuperHeroByName getSuperHeroByName;
    private final DeleteSuperHero deleteSuperHero;
    private final UpdateSuperHero updateSuperHero;

    public SuperHeroController() {
        SuperHeroRepository superHeroRepository = new InMemorySuperHeroRepository();
        getSuperHeroes = new GetSuperHeroes(superHeroRepository);
        getSuperHeroById = new GetSuperHeroById(superHeroRepository);
        getSuperHeroByName = new GetSuperHeroByName(superHeroRepository);
        deleteSuperHero = new DeleteSuperHero(superHeroRepository);
        updateSuperHero = new UpdateSuperHero(superHeroRepository);
    }

    @GetMapping(value = "/superheroes")
    public ResponseEntity<List<SuperHero>> getAll() {
        List<SuperHero> superHeroes = getSuperHeroes.execute();
        return ResponseEntity.ok().body(superHeroes);
    }

    @GetMapping(value = "/superheroes/{id}")
    public ResponseEntity<SuperHero> getById(@PathVariable String id) {
        Optional<SuperHero> superHero = getSuperHeroById.execute(id);
        if (superHero.isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok().body(superHero.get());
    }

    @GetMapping(value = "/superheroes/search")
    public ResponseEntity<List<SuperHero>> getByName(@RequestParam("name") String name) {
        List<SuperHero> superHeroes = getSuperHeroByName.execute(name);
        return ResponseEntity.ok().body(superHeroes);
    }

    @DeleteMapping(value = "/superheroes/{id}")
    public void deleteSuperHero(@PathVariable String id) {
        deleteSuperHero.execute(id);
    }

    @PutMapping(value = "/superheroes/{id}")
    public void updateSuperHero(@PathVariable String id, @RequestBody UpdateSuperHeroRequest updateSuperHeroRequest) {
        updateSuperHero.execute(id, updateSuperHeroRequest.getName());
    }
}