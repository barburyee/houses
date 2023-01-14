package com.kimeli.houses.repository;

import com.kimeli.houses.entity.House;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import java.util.List;
@Repository
public interface HousesRepository extends JpaRepository<House, Integer>, JpaSpecificationExecutor<House> {
    //List<House> findAllHouses(Predicate predicate);
    //List<House> findAllHouses(Specification<House> spec);

}
