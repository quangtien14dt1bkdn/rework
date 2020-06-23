package com.example.demo.models.service;

import javax.persistence.*;
import java.util.List;

@Entity
public class TypeRent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private float cost;

    @OneToMany(targetEntity = service.class)
    private List<service> serviceList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public List<service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<service> serviceList) {
        this.serviceList = serviceList;
    }
}
