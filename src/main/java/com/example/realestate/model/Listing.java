package com.example.realestate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "listing")
public class Listing {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @Column
    private String title;
    @Column
    @Enumerated(value = EnumType.STRING)
    private ListingType listingType;
    @Column
    private double price;
    @Column
    private int bedrooms;
    @Column
    private int bathrooms;
    @Column
    private double area;
    @Column
    private String mlsNo;
    @Column
    private String location;

    @ManyToMany
    @JoinTable(name = "listing_feature",
    joinColumns = {@JoinColumn(name = "listing_id")},
    inverseJoinColumns = {@JoinColumn(name = "feature_id")})
    private List<ListingFitchers> fitchersList;

    @Column
    private String picUrl;

    @Column
    @Enumerated(value = EnumType.STRING)
    private ListingStatus listingStatus = ListingStatus.NORMAL;

}
