package com.cheese.domain.domain.userRoles;

import com.cheese.domain.domain.admin.EAdminRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(	name = "user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EAdminRole name;

    @Column(length = 50)
    private String description;
}
