package com.cheese.domain.domain.storeUser;

import com.cheese.domain.domain.store.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreUserRepository extends JpaRepository<StoreUser, Long> {

    Page<StoreUser> findByNameContaining(String name, Pageable pageable);
    Page<StoreUser> findByEmailContaining(String email, Pageable pageable);
    List<StoreUser> findByNameContaining(String name, Sort sort);

}
