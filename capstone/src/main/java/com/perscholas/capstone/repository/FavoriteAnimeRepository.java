package com.perscholas.capstone.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.perscholas.capstone.entity.FavoriteAnime;

@Repository

public interface FavoriteAnimeRepository extends JpaRepository<FavoriteAnime, Long> {

  
	List<FavoriteAnime> findByIsFavoriteTrue();
	FavoriteAnime findByNameAndIsFavoriteTrue(String name);
	
	@Query("SELECT f FROM FavoriteAnime f WHERE f.name = :name AND f.isFavorite = true")
	List<FavoriteAnime> findFavoritesByName(String name);
	FavoriteAnime findByNameAndUserId(String animeName, Long id);
	
	   @Modifying
	   @Query("DELETE FROM FavoriteAnime f WHERE f.name = :name AND f.user.id = :userId")
	   void deleteByNameAndUserId(@Param("name") String name, @Param("userId") Long userId);
}

