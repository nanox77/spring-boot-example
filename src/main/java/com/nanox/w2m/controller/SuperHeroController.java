package com.nanox.w2m.controller;

import com.nanox.w2m.actions.*;
import com.nanox.w2m.controller.requests.UpdateSuperHeroRequest;
import com.nanox.w2m.domain.SuperHero;
import com.nanox.w2m.exceptions.SuperHeroNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
public class SuperHeroController {

    @Resource(name = "getSuperHeroById")
    private GetSuperHeroById getSuperHeroById;

    @Resource(name = "getSuperHeroByName")
    private GetSuperHeroByName getSuperHeroByName;

    @Resource(name = "deleteSuperHero")
    private DeleteSuperHero deleteSuperHero;

    @Resource(name = "updateSuperHero")
    private UpdateSuperHero updateSuperHero;

    @Resource(name = "getSuperHeroes")
    private GetSuperHeroes getSuperHeroes;


    @GetMapping(value = "/api/superheroes")
    public ResponseEntity<List<SuperHero>> getAll() {
        List<SuperHero> superHeroes = getSuperHeroes.execute();
        return ResponseEntity.ok().body(superHeroes);
    }

    @GetMapping(value = "/api/superhero/{id}")
    public ResponseEntity<SuperHero> getById(@PathVariable String id) {
        Optional<SuperHero> superHero = getSuperHeroById.execute(id);
        if (superHero.isEmpty()) {
            throw new SuperHeroNotFoundException(String.format("SuperHero with id %s not found", id));
        }
        return ResponseEntity.ok().body(superHero.get());
    }

    @GetMapping(value = "/api/superheroes/search")
    public ResponseEntity<List<SuperHero>> getByName(@RequestParam("name") String name) {
        List<SuperHero> superHeroes = getSuperHeroByName.execute(name);
        return ResponseEntity.ok().body(superHeroes);
    }

    @DeleteMapping(value = "/api/superhero/{id}")
    public void deleteSuperHero(@PathVariable String id) {
        deleteSuperHero.execute(id);
    }

    @PutMapping(value = "/api/superhero/{id}")
    public void updateSuperHero(@PathVariable String id, @RequestBody UpdateSuperHeroRequest updateSuperHeroRequest) {
        updateSuperHero.execute(id, updateSuperHeroRequest.getName());
    }
}