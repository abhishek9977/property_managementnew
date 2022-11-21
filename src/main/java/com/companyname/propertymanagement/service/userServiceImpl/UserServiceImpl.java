package com.companyname.propertymanagement.service.userServiceImpl;

import com.companyname.propertymanagement.dto.PropertyDTO;
import com.companyname.propertymanagement.dto.UserDTO;
import com.companyname.propertymanagement.entity.PropertyEntity;
import com.companyname.propertymanagement.entity.UserEntity;
import com.companyname.propertymanagement.repository.PropertyRepository;
import com.companyname.propertymanagement.repository.UserRepository;
import com.companyname.propertymanagement.service.PropertyService;
import com.companyname.propertymanagement.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService
{


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO register(UserDTO userDTO)
    {
        UserEntity userEntity=new UserEntity();
        BeanUtils.copyProperties(userDTO,userEntity);
        userEntity=userRepository.save(userEntity);
        BeanUtils.copyProperties(userEntity,userDTO);
        userDTO.setPassword(null);
        return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) {
        return null;
    }
}
