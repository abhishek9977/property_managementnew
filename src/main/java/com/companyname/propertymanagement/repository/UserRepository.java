package com.companyname.propertymanagement.repository;

import com.companyname.propertymanagement.entity.PropertyEntity;
import com.companyname.propertymanagement.entity.UserEntity;
import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>
{
   Optional<UserEntity> findByOwnerEmailAndPassword(String email,String password);
   Optional<UserEntity> findByOwnerEmail(String email);

}
