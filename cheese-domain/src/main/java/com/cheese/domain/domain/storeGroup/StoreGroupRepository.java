package com.cheese.domain.domain.storeGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreGroupRepository extends JpaRepository<StoreGroup, Long> {
}
