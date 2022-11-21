package com.companyname.propertymanagement.repository;

import com.companyname.propertymanagement.entity.PropertyEntity;
import com.companyname.propertymanagement.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {


}