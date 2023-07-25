package com.perscholas.capstone.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.perscholas.capstone.repository.*;
import com.perscholas.capstone.entity.*;
import com.perscholas.capstone.entity.User;

import com.perscholas.capstone.repository.FavoriteAnimeRepository;
//List<FavoriteAnime> favoriteAnimes = favoriteAnimeRepository.findAll();
import com.perscholas.capstone.service.CustomUserDetailsService;

import jakarta.servlet.http.HttpServletRequest;

//import jakarta.transaction.Transactional;

import com.perscholas.capstone.entity.*;

@Controller
public class FavoriteAnimeController {
    private final FavoriteAnimeRepository favoriteAnimeRepository;
    private final UserRepository userRepository;

    @Autowired
    public FavoriteAnimeController(FavoriteAnimeRepository favoriteAnimeRepository, UserRepository userRepository) {
        this.favoriteAnimeRepository = favoriteAnimeRepository;
        this.userRepository = userRepository;
    }
    
    @GetMapping("/favorites")
    public String showFavorites(Model model, Authentication authentication) {
        CustomUserDetailsService.CustomUserDetails userDetails = (CustomUserDetailsService.CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();

        // get user through custom method
        User userWithFavorites = userRepository.findUserWithFavoriteAnimesByUserId(user.getId());
        // Pass the User object to the favorites html?
        model.addAttribute("user", userWithFavorites);
        return "favorites";
    }

    @PostMapping("/favorite/add")
    @Transactional
    public String favoriteAnimePage(@RequestParam("animeName") String animeName, HttpServletRequest request, Authentication authentication , RedirectAttributes redirectAttributes ) {
    	CustomUserDetailsService.CustomUserDetails userDetails = (CustomUserDetailsService.CustomUserDetails) authentication.getPrincipal();
    	   String username = userDetails.getUsername();

    	    // get user through method
    	    User user = userRepository.findUserWithFavoriteAnimesByUsername(username);

    	    // Check if user has already favorited 
    	    FavoriteAnime favoriteAnime = user.getFavoriteAnimes()
    	            .stream()
    	            .filter(f -> f.getName().equals(animeName) && f.isFavorite())
    	            .findFirst()
    	            .orElse(null);

    	    if (favoriteAnime == null) {
    	        // Anime is not favorited by the user, so create new entry
    	        favoriteAnime = new FavoriteAnime(animeName, true);
    	        favoriteAnime.setUser(user);
    	        user.getFavoriteAnimes().add(favoriteAnime); // Add the favorite to the user's list
    	    } else {
    	        // Anime is already favorited by the user, so just update entry to true
    	        favoriteAnime.setFavorite(true);
    	    }

    	    favoriteAnimeRepository.save(favoriteAnime);
    	    userRepository.save(user); // save anime to the user
    	    redirectAttributes.addFlashAttribute("flashMessage", "Anime successfully faarited added: " + animeName);
            String referrer = request.getHeader("referer");

            // Redirect back to the previous page
            return "redirect:" + referrer;
    	}

    @PostMapping("/unfavorite/{animeName}")
    @Transactional
    public String unfavoriteAnime(@PathVariable String animeName, RedirectAttributes redirectAttributes, Authentication authentication) {
    	   CustomUserDetailsService.CustomUserDetails userDetails = (CustomUserDetailsService.CustomUserDetails) authentication.getPrincipal();
    	    User user = userDetails.getUser();

    	    // delete favorite by anime name and user ID
    	    favoriteAnimeRepository.deleteByNameAndUserId(animeName, user.getId());
    	    System.out.println("Unfavoriting anime with name: " + animeName);

    	    // success message saying unfavorite
    	    redirectAttributes.addFlashAttribute("success", "Anime successfully unfavorited: " + animeName);

    	    return "redirect:/favorites";
    }
}
    	
