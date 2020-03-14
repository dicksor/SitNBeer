package com.example.demo.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id")
  private Integer id;

  @Column(name = "name")
  @NotEmpty(message = "*Please provide a name")
  private String name;

  @Column(name = "password")
  @NotEmpty(message = "*Please provide your password")
  private String password;

  @Column(name = "email")
  private String email;

  @Column(name = "active")
  private Integer active;

  @JoinColumn(name = "role_id")
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  private Role role;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setActive(Integer active) {
    this.active = active;
  }

  public Integer getActive() {
    return active;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

}
