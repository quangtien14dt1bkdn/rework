package com.example.demo.models.customer;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;


@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Size(min = 3,max = 50)
    private String name;

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    @NotEmpty
    @Pattern(regexp = "^[0-9]{9}$", message = "may contain 9 numbers")
    private String idPerson;

//    @NotNull(message = "notnull")
    @NotEmpty
    @Pattern(regexp = "^((090)|(091))[0-9]{7}$", message = "Sdt có 10 số,bắt đầu bằng 090/091")
    private String phoneNumber;

//    @Pattern(regexp = "^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$", message = "chua dung dinh dang email")
//    @Email
    private String email;

    private String address;

    @ManyToOne
    private TypeCustomer typeCustomer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeCustomer getTypeCustomer() {
        return typeCustomer;
    }

    public void setTypeCustomer(TypeCustomer typeCustomer) {
        this.typeCustomer = typeCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(String idPerson) {
        this.idPerson = idPerson;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
