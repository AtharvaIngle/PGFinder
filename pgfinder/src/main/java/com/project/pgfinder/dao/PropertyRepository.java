package com.project.pgfinder.dao;

import com.project.pgfinder.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByLocation(String location); // Custom query to filter by location
}