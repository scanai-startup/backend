package com.scanai.api.repositories;

import com.scanai.api.domain.uva.Uva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UvaRepository extends JpaRepository<Uva, Long> {

    List<Uva> findAllByValidTrue();

}
