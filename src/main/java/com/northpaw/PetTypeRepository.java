package com.northpaw;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetTypeRepository extends JpaRepository<PetType, Long> {
    List<PetType> findAllByOrderByNameAsc();
}
