package com.nanox.w2m.infrastructure;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface H2CrudRepository extends CrudRepository<SuperHeroEntity, String> {
}
