package com.cheese.domain.domain.store;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    Page<Store> findByNameContaining(String name, Pageable pageable);
    Page<Store> findByCeoNameContaining(String ceoName, Pageable pageable);
    List<Store> findByNameContaining(String name, Sort sort);
}
