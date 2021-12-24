package com.cheese.domain.domain.users;

import com.cheese.domain.domain.storeUser.StoreUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByNameContaining(String name, Pageable pageable);
    Page<User> findByCpContaining(String cp, Pageable pageable);
    List<User> findByNameContaining(String name, Sort sort);
}
