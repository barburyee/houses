package com.kimeli.houses.service;

import com.kimeli.houses.entity.House;

import java.util.List;

public interface HouseService {
    public List<House> searchHousesMain(String estate, String houseNumber, String street, Integer numberOfBedrooms, String tenantName, Long tenantPhone, String date_created_start, String date_created_end);
    public House addHouseDetails(House house);
    List<House> getAllHouses();
}
