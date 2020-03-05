package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "bar")
public class Bar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn
    private User user;

    @Column(name = "name")
    @NotEmpty(message = "*Please provide a name")
    private String name;

    @Column(name = "address")
    @NotEmpty(message = "*Please provide an address")
    private String address;

    @Column(name = "available_table")
    @NotEmpty(message = "*Please provide a number of available tables")
    private String availableTable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvailableTable() {
        return availableTable;
    }

    public void setAvailableTable(String availableTable) {
        this.availableTable = availableTable;
    }

}
