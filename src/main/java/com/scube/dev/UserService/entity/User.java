package com.scube.dev.UserService.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @SequenceGenerator(
            name = "userId",
            sequenceName = "userId",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "userId"
    )

    public long userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    public String userEnrollDate;

    @Column(nullable = false)
    public String userFullName;

    public String approveBy;

    public String updateBy;
}
