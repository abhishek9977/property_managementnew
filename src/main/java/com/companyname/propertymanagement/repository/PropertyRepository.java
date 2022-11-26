package com.companyname.propertymanagement.repository;

import com.companyname.propertymanagement.entity.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<PropertyEntity, Long>
{

    List<PropertyEntity> findAllByUserEntityId(Long userId);

}
