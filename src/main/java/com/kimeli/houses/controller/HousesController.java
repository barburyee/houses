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

    @RequestMapping("/")
    public String searchHousesPredNow(Model model){
        List<House> allHouses = houseService.getAllHouses();
        model.addAttribute("searchedHouses",allHouses);
        model.addAttribute("search","NO");
        return "index";
    }

    @RequestMapping("/addHousePred")
    public String addHousePredNow(Model model, @RequestParam("estate") String estate,
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
        houseService.addHouseDetails(house);
        List<House> allHouses = houseService.getAllHouses();
        model.addAttribute("searchedHouses",allHouses);
        model.addAttribute("search","NO");
        return "search_house";

    }

    @RequestMapping("/searchHousesPred")
    public String searchHousesPredNow(Model model,
            @RequestParam(name = "estate", required = false) String estate,
            @RequestParam(name = "houseNumber", required = false) String houseNumber,
            @RequestParam(name = "street", required = false) String street,
            @RequestParam(name = "numberOfBedrooms", required = false) Integer numberOfBedrooms,
            @RequestParam(name = "tenantName", required = false) String tenantName,
            @RequestParam(name = "tenantPhone", required = false) Long tenantPhone,
            @RequestParam(name = "date_created_start", required = false) String dateCreatedStart,
            @RequestParam(name = "date_created_end", required = false) String dateCreatedEnd
    ) {
        try {
            System.out.println("\n    Es:"+estate+"   hN:"+houseNumber+" st:"+street+"    no:"+numberOfBedrooms+" name:"+tenantName+"  phn"+tenantPhone+"    crAt:"+dateCreatedStart+"   cr:"+dateCreatedEnd);
            List<House> searchedHouses = houseService.searchHousesMain(estate, houseNumber, street, numberOfBedrooms, tenantName, tenantPhone, dateCreatedStart, dateCreatedEnd);
            model.addAttribute("searchedHouses",searchedHouses);

            model.addAttribute("search","YES");
            System.out.println("\nRecords On Searching: "+searchedHouses.size());
            return "search_house";
        }catch(Exception exception){
            return null;
        }
    }

    @RequestMapping("/addNewHouse")
    public String addNewHouse(){
        return "addHouse";
    }

    @RequestMapping("/searchHouses")
    @ResponseBody
    public List<House> searchHousesNow(
            @RequestParam(name = "estate", required = false) String estate,
            @RequestParam(name = "houseNumber", required = false) String houseNumber,
            @RequestParam(name = "street", required = false) String street,
            @RequestParam(name = "numberOfBedrooms", required = false) Integer numberOfBedrooms,
            @RequestParam(name = "tenantName", required = false) String tenantName,
            @RequestParam(name = "tenantPhone", required = false) Long tenantPhone,
            @RequestParam(name = "date_created_start", required = false) String dateCreatedStart,
            @RequestParam(name = "date_created_end", required = false) String dateCreatedEnd
    ) {

        return houseService.searchHousesMain(estate,houseNumber,street,numberOfBedrooms,tenantName,tenantPhone, dateCreatedStart,dateCreatedEnd);
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
