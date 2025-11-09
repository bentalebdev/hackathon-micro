package com.example.registrationservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_group")
    private Long idGroup;

    private String label;
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User gerant;

    @ManyToMany(mappedBy = "groups")
    private List<User> users;
}