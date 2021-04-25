package com.nanox.w2m.configuration;


import com.nanox.w2m.actions.*;
import com.nanox.w2m.domain.SuperHeroRepository;
import com.nanox.w2m.infrastructure.H2CrudRepository;
import com.nanox.w2m.infrastructure.H2SuperHeroRepository;
import com.nanox.w2m.infrastructure.InMemorySuperHeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.nanox.w2m.infrastructure"})
@ComponentScan("com.nanox.w2m")
public class SuperHeroConfiguration {

    @Bean
    public SuperHeroRepository h2SuperHeroRepository(@Autowired H2CrudRepository h2CrudRepository) {
        return new H2SuperHeroRepository(h2CrudRepository);
    }

    @Bean
    public SuperHeroRepository superHeroRepository() {
        return new InMemorySuperHeroRepository();
    }

    @Bean
    public GetSuperHeroes getSuperHeroes(SuperHeroRepository h2SuperHeroRepository) {
        return new GetSuperHeroes(h2SuperHeroRepository);
    }

    @Bean
    public GetSuperHeroById getSuperHeroById(SuperHeroRepository h2SuperHeroRepository) {
        return new GetSuperHeroById(h2SuperHeroRepository);
    }

    @Bean
    public GetSuperHeroByName getSuperHeroByName(SuperHeroRepository h2SuperHeroRepository) {
        return new GetSuperHeroByName(h2SuperHeroRepository);
    }

    @Bean
    public DeleteSuperHero deleteSuperHero(SuperHeroRepository h2SuperHeroRepository) {
        return new DeleteSuperHero(h2SuperHeroRepository);
    }

    @Bean
    public UpdateSuperHero updateSuperHero(SuperHeroRepository h2SuperHeroRepository) {
        return new UpdateSuperHero(h2SuperHeroRepository);
    }
}
