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
import java.util.ArrayList;
import java.util.List;

import static org.thymeleaf.util.StringUtils.trim;

@Service
public class HouseServiceImp implements HouseService{
    @Autowired
    private HousesRepository houseRepository;

    /*@Autowired
    private EntityManager entityManager;*/

    /*@Override
    public List<House> searchHousesMain(String estate, String houseNumber, String street, Integer numberOfBedrooms, String tenantName, Long tenantPhone) {
        try {
            // Create the CriteriaBuilder
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();

            // Create the CriteriaQuery
            CriteriaQuery<House> query = cb.createQuery(House.class);
            Root<House> root = query.from(House.class);

            // Create the Predicate
            List<Predicate> predicates = new ArrayList<>();
            if (estate != null) {
                predicates.add(cb.like(root.get("estate"), "%" + estate + "%"));
            }
            if (houseNumber != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("house_number"), houseNumber));
            }
            if (street != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("street"), street));
            }

            if (numberOfBedrooms != null) {
                predicates.add(cb.like(root.get("number_of_bedrooms"), "%" + numberOfBedrooms + "%"));
            }
            if (tenantName != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("tenant_name"), tenantName));
            }
            if (tenantPhone != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("tenant_phone"), tenantPhone));
            }
            query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

            // Execute the query
            return houseRepository.findAllHouses((Specification<House>) query);
        }catch (Exception ex){
            System.out.println("Error In HouseServiceImp Class:\n"+ex.getMessage().toString());
            return null;
        }
    }
*/

    //private final HousesRepository housesRepository;

//    public HouseSearchService(HousesRepository housesRepository) {
//        this.housesRepository = housesRepository;
//    }

    public List<House> searchHousesMain(String estate, String houseNumber, String street, Integer numberOfBedrooms, String tenantName, Long tenantPhone) {

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
                //p = builder.and(p, builder.equal(root.get("numberOfBedrooms"), "%" + numberOfBedrooms + "%"));
                p = builder.and(p, builder.equal(root.get("numberOfBedrooms"),  trim(numberOfBedrooms )));
            }
            if (tenantName != null) {
                p = builder.and(p, builder.equal(root.get("tenantName"), trim(tenantName)));
            }
            if (tenantPhone != null) {
                p = builder.and(p, builder.equal(root.get("tenantPhone"), trim(tenantPhone)));
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
}
