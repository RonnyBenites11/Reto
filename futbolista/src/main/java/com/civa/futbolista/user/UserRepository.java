package com.civa.futbolista.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {
    Optional<User> findByUsername(String username);

    @Modifying()
    @Query("update User u set u.nombres=:nombres  where u.id = :id")
    void updateUser(@Param(value = "id") Long id, @Param(value = "nombres") String nombres);

}
