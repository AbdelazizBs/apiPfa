package com.azzouz.apipfa.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role")
@NoArgsConstructor
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "role_id")
  private long id;

  @Column(name = "role")
  private String role;

  public Role(final long id, final String role) {
    this.id = id;
    this.role = role;
  }

  /* @ManyToOne(cascade = CascadeType.ALL)
  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name
          = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
  private User user;*/
}
