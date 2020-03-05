package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @NotEmpty(message = "*Please provide a user")
    private User user;

    @OneToOne
    @JoinColumn(name = "bar_id")
    @NotEmpty(message = "*Please provide a bar")
    private Bar bar;

    @OneToOne
    @JoinColumn(name = "beer_id")
    @NotEmpty(message = "*Please provide a beer")
    private Beer beer;

    @Column(name = "table")
    @NotEmpty(message = "*Please provide a table")
    private Integer table;

    @Column(name = "status")
    @NotEmpty(message = "*Please provide a status")
    private int status;

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

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    public Integer getTable() {
        return table;
    }

    public void setTable(Integer table) {
        this.table = table;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
