package com.nanox.w2m.infrastructure;

import com.nanox.w2m.domain.SuperHero;
import org.springframework.data.repository.CrudRepository;

public interface H2CrudRepository extends CrudRepository<SuperHero, String> {
}
