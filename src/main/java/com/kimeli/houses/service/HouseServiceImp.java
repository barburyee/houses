package com.kimeli.houses.service;

import com.kimeli.houses.entity.House;
import com.kimeli.houses.repository.HousesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.net.URLDecoder;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.thymeleaf.util.StringUtils.trim;

@Service
public class HouseServiceImp implements HouseService{
    @Autowired
    private HousesRepository houseRepository;


    private Date getDateTimestampFromString(String dateToBeChanged) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = (Date) dateFormat.parse(dateToBeChanged);
            return date;
        }catch (Exception ex){
            return null;
        }
    }

    public List<House> searchHousesMain(String estate, String houseNumber, String street, Integer numberOfBedrooms, String tenantName, Long tenantPhone, String date_created_start, String date_created_end) {

        Specification<House> spec = (root, query, builder) -> {
            Predicate p = builder.conjunction();

            if (estate != null) {
                p = builder.and(p, builder.equal(root.get("estate"), trim(estate) ));
            }
            if (houseNumber != null) {
                p = builder.and(p, builder.equal(root.get("houseNumber"), trim(houseNumber)));
            }
            if (street != null) {
                p = builder.and(p, builder.equal(root.get("street"), trim(street)));
            }
            if (numberOfBedrooms != null) {
                p = builder.and(p, builder.equal(root.get("numberOfBedrooms"),  trim(numberOfBedrooms )));
            }
            if (tenantName != null) {
                p = builder.and(p, builder.equal(root.get("tenantName"), trim(tenantName)));
            }
            if (tenantPhone != null) {
                p = builder.and(p, builder.equal(root.get("tenantPhone"), trim(tenantPhone)));
            }

            if (getDateTimestampFromString(date_created_start) != null && getDateTimestampFromString(date_created_end) != null) {
                p = builder.and(p, builder.between(root.get("dateCreated"), getDateTimestampFromString(date_created_start), getDateTimestampFromString(date_created_end)));
            }

            return p;
        };
        return houseRepository.findAll(spec);
    }

    @Override
    public House addHouseDetails(House house) {
        houseRepository.save(house);
        return  house;
    }

    @Override
    public List<House> getAllHouses() {
        return houseRepository.findAll();
    }
}
