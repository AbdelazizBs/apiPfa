package com.azzouz.apipfa.entities;

import com.azzouz.apipfa.entities.enums.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User {
  @javax.persistence.Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "active")
  private int active;

  @Column(name = "username")
  private String username;

  @Email
  @Column(name = "email")
  private String email;

  @Column(name = "address")
  private String address;

  @Column private String password;

  @Column(name = "phone")
  private Long phoneNumber;

  @Enumerated(EnumType.STRING)
  @Column(name = "category")
  private Category preferedCategory;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;

  @ManyToOne
  @JoinColumn(name = "location_id" /*, nullable = false*/)
  private Location location;

  /* @Enumerated(EnumType.STRING)
      @Column(name = "role")
      private Role role;
      @ManyToMany(cascade = CascadeType.ALL)
      @JoinTable(name = "user_role", joinColumns = @JoinColumn(name
              = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
      private Set<Role> roles;

  */
}
