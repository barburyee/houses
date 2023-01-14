package com.kimeli.houses.controller;

import com.kimeli.houses.entity.House;
import com.kimeli.houses.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Controller
public class HousesController {

    @Autowired
    private HouseService houseService;

    @RequestMapping("/searchHouses")
    @ResponseBody
    public List<House> searchHousesNow(
            @RequestParam(name = "estate", required = false) String estate,
            @RequestParam(name = "houseNumber", required = false) String houseNumber,
            @RequestParam(name = "street", required = false) String street,
            @RequestParam(name = "numberOfBedrooms", required = false) Integer numberOfBedrooms,
            @RequestParam(name = "tenantName", required = false) String tenantName,
            @RequestParam(name = "tenantPhone", required = false) Long tenantPhone
    ) {
        //String noOfBeds = URLDecoder.decode(numberOfBedrooms, "UTF-8");
        /*Model model, @RequestParam("estate") String estate,
                                       @RequestParam("houseNumber") String houseNumber,
                                       @RequestParam("street") String street,
                                       @RequestParam("numberOfBedrooms") Integer numberOfBedrooms,
                                       @RequestParam("tenantName") String tenantName,
                                       @RequestParam("tenantPhone") Long tenantPhone
                                       */

        return houseService.searchHousesMain(estate,houseNumber,street,numberOfBedrooms,tenantName,tenantPhone);
        //return null;
    }

    @RequestMapping("/addHouse")
    @ResponseBody
    public House addHouseNow(Model model, @RequestParam("estate") String estate,
                                       @RequestParam("houseNumber") String houseNumber,
                                       @RequestParam("street") String street,
                                       @RequestParam("numberOfBedrooms") Integer numberOfBedrooms,
                                       @RequestParam("tenantName") String tenantName,
                                       @RequestParam("tenantPhone") Long tenantPhone
    ){
        House house = new House();
        house.setHouseNumber(houseNumber);
        house.setEstate(estate);
        house.setNumberOfBedrooms(numberOfBedrooms);
        house.setStreet(street);
        house.setTenantName(tenantName);
        house.setTenantPhone(tenantPhone);
        house.setStatus(1);
        return houseService.addHouseDetails(house);

    }
}
