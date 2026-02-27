package com.adrar.cdafad.repository;

import com.adrar.cdafad.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {
    public Optional<Users> findByEmail(String email);
}
