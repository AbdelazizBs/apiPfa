package com.azzouz.apipfa.entities;

import com.azzouz.apipfa.entities.enums.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "publicite")
@Getter
@Setter
@NoArgsConstructor
public class Publicite {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "description")
  private String description;

  /*@Column(name = "picture")
  private String picture;*/

  @Column(name = "date")
  private LocalDateTime date = LocalDateTime.now();

  @Enumerated(EnumType.STRING)
  @Column(name = "category")
  private Category category;

  @ManyToOne
  @JoinColumn(name = "user_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private User user;

  @ManyToOne
  @JoinColumn(name = "locationCity" /*, nullable = false*/)
  private Location location;

  @OneToMany private List<Picture> picture;
}
