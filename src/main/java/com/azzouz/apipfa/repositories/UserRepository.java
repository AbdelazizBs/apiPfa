package com.azzouz.apipfa.repositories;

import com.azzouz.apipfa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);

  User findByUsernameAndPassword(String username, String password);

  User findByUsername(String username);
}
