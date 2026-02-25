package com.adrar.cdafad.repository;

import com.adrar.cdafad.entity.Manufacturer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends CrudRepository<Manufacturer, Integer> {
    public boolean existsByName(String name);
}
