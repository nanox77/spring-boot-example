package com.nanox.w2m.configurations;


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
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
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

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.nanox.w2m.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
