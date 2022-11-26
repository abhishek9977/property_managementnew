package com.companyname.propertymanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO
{
    private Long id;
    private String ownerName;
    @NotNull(message = "owner email is mandatory")
    @NotEmpty(message = "not empty")
    @Size(min=1,max=50)
    private String ownerEmail;
    private String password;
    private String phone;
}
