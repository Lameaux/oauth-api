package com.euromoby.oauth.repos;

import com.euromoby.oauth.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {

}
