package com.cheese.domain.domain.adminRolePrivilege;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AdminRolePrivilegeRepository extends JpaRepository<AdminRolePrivilege, Long> {
    Optional<AdminRolePrivilege> findByName(Enum name);
}
