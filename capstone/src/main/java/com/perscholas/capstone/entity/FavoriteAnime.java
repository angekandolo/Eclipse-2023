package com.perscholas.capstone.entity;
import java.util.Objects;

import jakarta.persistence.*;

@Entity

public class FavoriteAnime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "animeName")
    private String name;

    private boolean isFavorite;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;



    
    public FavoriteAnime() {
    }

    public FavoriteAnime(String name, boolean isFavorite) {
        this.name = name;
        this.isFavorite = isFavorite;
    }

 

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteAnime that = (FavoriteAnime) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
  

}
