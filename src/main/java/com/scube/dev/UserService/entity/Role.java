package com.scube.dev.UserService.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role {

    @Id
    @SequenceGenerator(
            name = "roleId",
            sequenceName = "roleId",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "roleId"
    )

    public long roleId;

    @Column(nullable = false)
    private String roleName;
}
