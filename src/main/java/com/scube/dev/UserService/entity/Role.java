package com.scube.dev.UserService.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @SequenceGenerator(
            name = "role_id_gen",
            sequenceName = "role_id_gen",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "role_id_gen"
    )
    private long id;

    @Column(length = 60, nullable = false)
    private String name;
}
