package com.kimeli.houses.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "houses")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name="estate")
    private String estate;
    @Column(name="house_number")
    private String houseNumber;
    @Column(name="street")
    private String street;
    @Column(name="number_of_bedrooms")
    private Integer numberOfBedrooms;
    @Column(name="tenant_name")
    private String tenantName;
    @Column(name="tenant_phone")
    private Long tenantPhone;
    @Column(name="status")
    private Integer status;
    @Column(name="date_created")
    private Timestamp dateCreated;
    @Column(name="date_modified")
    private Timestamp dateModified;

    public House() {
    }

    public House(String estate, String houseNumber, String street, Integer numberOfBedrooms, String tenantName, Long tenantPhone, Integer status) {
        this.estate = estate;
        this.houseNumber = houseNumber;
        this.street = street;
        this.numberOfBedrooms = numberOfBedrooms;
        this.tenantName = tenantName;
        this.tenantPhone = tenantPhone;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstate() {
        return estate;
    }

    public void setEstate(String estate) {
        this.estate = estate;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(Integer numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public Long getTenantPhone() {
        return tenantPhone;
    }

    public void setTenantPhone(Long tenantPhone) {
        this.tenantPhone = tenantPhone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Timestamp getDateModified() {
        return dateModified;
    }

    public void setDateModified(Timestamp dateModified) {
        this.dateModified = dateModified;
    }
}
