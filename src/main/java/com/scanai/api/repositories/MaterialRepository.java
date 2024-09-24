package com.scanai.api.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.scanai.api.domain.material.Material;


public interface MaterialRepository extends JpaRepository<Material, Long>{
}
