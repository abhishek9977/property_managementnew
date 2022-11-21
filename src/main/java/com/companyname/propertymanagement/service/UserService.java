package com.companyname.propertymanagement.service;

import com.companyname.propertymanagement.dto.UserDTO;

public interface UserService
{

     UserDTO register(UserDTO userDTO);
     UserDTO login(String email,String password);

}
