/**
 * SitNBeer
 * Romain Capocasale, Vincent Moulin and Jonas Freiburghaus
 * He-Arc, INF3dlm-a
 * Spring Course
 * 2019-2020
 */

package ch.hearc.sitnbeer.models;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bars")
public class Bar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "bar", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Beer> beers;

    @Column(name = "name")
    @NotEmpty(message = "*Please provide a name")
    private String name;

    @Column(name = "address")
    @NotEmpty(message = "*Please provide an address")
    private String address;

    @Column(name = "available_table")
    @NotNull(message = "*Please provide a number of available tables")
    private int availableTable;

    @OneToMany(mappedBy = "bar", fetch = FetchType.EAGER)
    private List<Order> orders;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public int getAvailableTable() {
        return availableTable;
    }

    public void setAvailableTable(int availableTable) {
        this.availableTable = availableTable;
    }

    public Set<Beer> getBeers(){
        return this.beers;
    }

    public void setBeers(Set<Beer> beers){
        this.beers = beers;
    }

    public List<Order> getOrders(){
        return this.orders;
    }

    public void setOrders(List<Order> orders){
        this.orders = orders;
    }
}
