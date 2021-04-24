package com.example.realestate.controler;

import com.example.realestate.config.WebSecurityConfig;
import com.example.realestate.model.CarentUser;
import com.example.realestate.model.Listing;
import com.example.realestate.model.ListingStatus;
import com.example.realestate.model.User;
import com.example.realestate.repository.ListingRepository;
import com.example.realestate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ListingRepository listingRepository;

    @GetMapping("/")
    public String main(ModelMap modelMap, @AuthenticationPrincipal CarentUser curentUser) {
        if (curentUser != null) {
            modelMap.addAttribute("carentUser", curentUser.getUser());
        }
        List<Listing> featuredList = listingRepository.findAllByListingStatus(ListingStatus.FEATURED);
        List<Listing>latestThre = listingRepository.findTop3ByOrdOrderByIdDesc();
        modelMap.addAttribute("featuredList",featuredList);
        modelMap.addAttribute("latestThre",latestThre);
        return "index";
    }

    @GetMapping("/search")
    public String search(ModelMap modelMap,@RequestParam("keyword") String keyword){

        List<Listing> searchResalt = listingRepository.findAllByTitleIgnoreCaseContaining(keyword);
        modelMap.addAttribute("listings",searchResalt);
        return "search";

    }
    @GetMapping("/signIn")
    public String signIn(ModelMap modelMap, @AuthenticationPrincipal CarentUser curentUser) {
        if (curentUser != null) {
            modelMap.addAttribute("carentUser", curentUser.getUser());
        }
        return "signin";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/signIn";
    }

    @GetMapping("/register")
    public String registerGet(ModelMap modelMap, @AuthenticationPrincipal CarentUser curentUser) {
        if (curentUser != null) {
            modelMap.addAttribute("carentUser", curentUser.getUser());
        }
        return "register";
    }

}
