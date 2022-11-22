package com.companyname.propertymanagement.service.userServiceImpl;

import com.companyname.propertymanagement.dto.PropertyDTO;
import com.companyname.propertymanagement.dto.UserDTO;
import com.companyname.propertymanagement.entity.PropertyEntity;
import com.companyname.propertymanagement.entity.UserEntity;
import com.companyname.propertymanagement.exception.BusinessException;
import com.companyname.propertymanagement.exception.ErrorModel;
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
        Optional<UserEntity> userEntity1=userRepository.findByOwnerEmail(userDTO.getOwnerEmail());
        if(userEntity1.isPresent())
        {
            List<ErrorModel> errorModels=new ArrayList<>();
            ErrorModel errorModel=new ErrorModel();
            errorModel.setCode("EMAIL ALREADY EXIST");
            errorModel.setMessage("The email is already used");
            errorModels.add(errorModel);
            throw new BusinessException(errorModels);
        }
        BeanUtils.copyProperties(userDTO,userEntity);
        userEntity=userRepository.save(userEntity);
        BeanUtils.copyProperties(userEntity,userDTO);
        userDTO.setPassword(null);
        return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) {

        Optional<UserEntity> userEntity = userRepository.findByOwnerEmailAndPassword(email, password);
        UserDTO userDTO = new UserDTO();
        if (userEntity.isPresent())
        {

            //UserEntity userEntity1=userEntity.get();
            BeanUtils.copyProperties(userEntity.get(), userDTO);
        }
        else
        {
            List<ErrorModel> errorModels=new ArrayList<>();
            ErrorModel errorModel=new ErrorModel();
            errorModel.setCode("INVALID_LOGIN");
            errorModel.setMessage("INCORRECT EMAIL AND PASSWORD");
            errorModels.add(errorModel);
            throw new BusinessException(errorModels);
        }
        userDTO.setPassword(null);
        return userDTO;
    }
}
