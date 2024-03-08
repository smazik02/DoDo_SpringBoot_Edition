package com.stanley.dodospring.repositories;

import com.stanley.dodospring.domain.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
