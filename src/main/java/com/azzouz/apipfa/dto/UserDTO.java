package com.azzouz.apipfa.dto;

import com.azzouz.apipfa.entities.Picture;
import com.azzouz.apipfa.entities.Role;
import com.azzouz.apipfa.entities.enums.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

  private Long id;
  private int active;
  private String username;
  private String email;
  private Category preferedCategory;
  private String address;
  private String password;
  private Long phoneNumber;
  private Set<Role> roles;
  private String locationCity;
  private Picture picture;
}
