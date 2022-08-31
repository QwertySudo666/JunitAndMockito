package com.example.junitandmockito.repository;

import com.example.junitandmockito.entity.PropertyEntity;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {

}

