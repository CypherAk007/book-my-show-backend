package com.backend.BookMyShowBackend.repositories;

import com.backend.BookMyShowBackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
//    JpaRepository<Object(you want to comm.),(primarykeyType)>

    //    Select * from user where email = 'given email'  - internal query of below declaration

    Optional<User> findByEmail(String email);
//    Column name(email) should match with findBy"Email"
    //    if we want to pass multiple parameters
    //    Optional<User> findByIdAndEmail(@Param("email") String email,@Param("id") Long id);

        //    if we want to find by email and password
    //    Optional<User> findByEmailAndEmail(String email,String password);

}
