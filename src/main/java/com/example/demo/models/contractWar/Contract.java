package com.example.demo.models.contractWar;

import com.example.demo.models.customer.Customer;
import com.example.demo.models.employee.Employee;
import com.example.demo.models.service.service;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;


@Entity


public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date contracted;
    private Date end;
    private String deposits;
    // tien dat coc
    private String cost;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private com.example.demo.models.service.service service;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getContracted() {
        return contracted;
    }

    public void setContracted(Date contracted) {
        this.contracted = contracted;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getDeposits() {
        return deposits;
    }

    public void setDeposits(String deposits) {
        this.deposits = deposits;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public com.example.demo.models.service.service getService() {
        return service;
    }

    public void setService(com.example.demo.models.service.service service) {
        this.service = service;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
