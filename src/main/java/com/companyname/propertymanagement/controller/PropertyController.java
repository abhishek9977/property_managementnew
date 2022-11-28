package com.companyname.propertymanagement.controller;


import com.companyname.propertymanagement.dto.PropertyDTO;
import com.companyname.propertymanagement.service.PropertyService;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PropertyController<Void>
{
    @Autowired
    private PropertyService propertyService;

    //@GetMapping("/hello")
    public String sayHello()
    {
        return  "hello";
    }

    @MutationMapping("createProperty")
    public PropertyDTO saveProperty(@Argument PropertyInput propertyDTO)
    {
        PropertyDTO prop=new PropertyDTO();
        BeanUtils.copyProperties(propertyDTO,prop);
        propertyService.saveProperty(prop);
        //ResponseEntity<PropertyDTO> re=new ResponseEntity<>(propertyDTO,HttpStatus.CREATED);
        return prop;
    }

    @QueryMapping("allProperty")
    public List<PropertyDTO> getAllProperties()
    {
        List<PropertyDTO> allProperties=propertyService.getAllProperties();
        //ResponseEntity<List<PropertyDTO>> listResponseEntity=new ResponseEntity<>(allProperties,HttpStatus.OK);
        return allProperties;
    }

    @QueryMapping("allPropertyByID")
    public List<PropertyDTO> getPropertiesById(@Argument Long userId)
    {
        List<PropertyDTO> allProperties=propertyService.getPropertiesById(userId);
        //ResponseEntity<List<PropertyDTO>> listResponseEntity=new ResponseEntity<>(allProperties,HttpStatus.OK);
        return allProperties;
    }

    //@PutMapping("/update/{propertyId}")
    public ResponseEntity<PropertyDTO> updateProperty(@RequestBody PropertyDTO propertyDTO,@PathVariable Long propertyId)
    {
        PropertyDTO propertyDTO1=propertyService.updateProperty(propertyDTO,propertyId);
        ResponseEntity<PropertyDTO> responseEntity=new ResponseEntity<>(propertyDTO1,HttpStatus.CREATED);
        return responseEntity;
    }

    //@PatchMapping("updated/{propertyId}")
    public ResponseEntity<PropertyDTO> updateDescription(@RequestBody PropertyDTO propertyDTO,@PathVariable Long propertyId)
    {
        PropertyDTO propertyDTO1=propertyService.updateDescription(propertyDTO,propertyId);
        ResponseEntity<PropertyDTO> propertyDTOResponseEntity=new ResponseEntity<>(propertyDTO1,HttpStatus.CREATED);
        return propertyDTOResponseEntity;
    }

    //@DeleteMapping("{propertyId}")
    public  ResponseEntity<Void> delete(@PathVariable Long propertyId)
    {
        propertyService.delete(propertyId);
        ResponseEntity<Void> responseEntity=new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        return responseEntity;
    }
}
@Setter
@Getter
class  PropertyInput
{
    private String title;
    private String description;
    private String ownerName;
    private String ownerEmail;
    private Double price;
    private String address;
}