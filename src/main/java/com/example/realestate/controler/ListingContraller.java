package com.example.realestate.controler;

import com.example.realestate.model.CarentUser;
import com.example.realestate.model.Listing;
import com.example.realestate.model.ListingFitchers;
import com.example.realestate.repository.ListingFeatureRepository;
import com.example.realestate.repository.ListingRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Optional;

@Controller
public class ListingContraller {

    @Autowired
    private ListingRepository listingRepository;
    @Autowired
    private ListingFeatureRepository listingFeatureRepository;

    @GetMapping("/listing/add")
    public String addListing(ModelMap modelMap, @AuthenticationPrincipal CarentUser curentUser) {
        if (curentUser != null) {
            modelMap.addAttribute("carentUser", curentUser.getUser());
        }
        List<ListingFitchers> featuresList = listingFeatureRepository.findAll();
        modelMap.addAttribute("features", featuresList);
        return "addListing";
    }

    @PostMapping("listing/add")
    public String addListingPost(@ModelAttribute Listing listing,
                                 @RequestParam("img") MultipartFile multipartFile,
                                 @RequestParam("features") List<Long> features) throws IOException {

        List<ListingFitchers> allById = listingFeatureRepository.findAllById(features);
        listing.setFitchersList(allById);
        String picName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
        File file = new File("C\\User\\Lenovo IdeaPad400\\Новая папка\\");
        multipartFile.transferTo(file);
        listing.setPicUrl(picName);
        listingRepository.save(listing);

        return "redirect:/listing/add";
    }

    @GetMapping("listing/image")
    public @ResponseBody byte[]userImage(@RequestParam ("picUrl") String picUrl) throws IOException {
        InputStream in = new FileInputStream("C\\User\\Lenovo IdeaPad400\\Новая папка\\" +picUrl);
                return IOUtils.toByteArray(in);
    }
}
