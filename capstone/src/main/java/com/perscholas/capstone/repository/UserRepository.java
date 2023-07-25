package com.perscholas.capstone.repository;
import com.perscholas.capstone.entity.*;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByName(String name);
    User findByUsername(String name);
    
    @EntityGraph(attributePaths = "favoriteAnimes")
    User findUserWithFavoriteAnimesByUsername(String username);
    

    @EntityGraph(attributePaths = "favoriteAnimes") 
    @Query("SELECT u FROM User u WHERE u.id = :userId")
    User findUserWithFavoriteAnimesByUserId(@Param("userId") Long userId);

}
