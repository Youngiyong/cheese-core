package com.cheese.domain.domain.adminRole;


import com.cheese.domain.domain.adminRolePrivilege.AdminRolePrivilege;
import com.cheese.domain.domain.enums.EAdminRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(	name = "admin_roles")
public class AdminRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EAdminRole name;

    @Column(length = 50)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "admin_role_privilege_join",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id"))
    private Set<AdminRolePrivilege> adminRolePrivileges = new HashSet<>();
}
