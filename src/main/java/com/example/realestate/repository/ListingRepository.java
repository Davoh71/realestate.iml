package com.example.realestate.repository;

import com.example.realestate.model.Listing;
import com.example.realestate.model.ListingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListingRepository extends JpaRepository<Listing,Long> {

    List<Listing> findAllByListingStatus(ListingStatus listingStatus);
    List<Listing> findTop3ByOrdOrderByIdDesc();
    List<Listing> findAllByTitleIgnoreCaseContaining(String keyword);
}
