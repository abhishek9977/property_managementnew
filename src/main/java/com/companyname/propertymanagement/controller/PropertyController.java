package com.companyname.propertymanagement.controller;


import com.companyname.propertymanagement.dto.PropertyDTO;
import com.companyname.propertymanagement.service.PropertyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController<Void>
{
    @Autowired
    private PropertyService propertyService;

    @GetMapping("/hello")
    public String sayHello()
    {
        return  "hello";
    }

    @PostMapping("/save")
    public ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO propertyDTO)
    {
        propertyService.saveProperty(propertyDTO);
        ResponseEntity<PropertyDTO> re=new ResponseEntity<>(propertyDTO,HttpStatus.CREATED);
        return re;
    }

    @GetMapping("/allProperty")
    public ResponseEntity<List<PropertyDTO>> getAllProperties()
    {
        List<PropertyDTO> allProperties=propertyService.getAllProperties();
        ResponseEntity<List<PropertyDTO>> listResponseEntity=new ResponseEntity<>(allProperties,HttpStatus.OK);
        return listResponseEntity;
    }

    @GetMapping("/allProperty/{userId}")
    public ResponseEntity<List<PropertyDTO>> getPropertiesById(@PathVariable Long userId)
    {
        List<PropertyDTO> allProperties=propertyService.getPropertiesById(userId);
        ResponseEntity<List<PropertyDTO>> listResponseEntity=new ResponseEntity<>(allProperties,HttpStatus.OK);
        return listResponseEntity;
    }

    @PutMapping("/update/{propertyId}")
    public ResponseEntity<PropertyDTO> updateProperty(@RequestBody PropertyDTO propertyDTO,@PathVariable Long propertyId)
    {
        PropertyDTO propertyDTO1=propertyService.updateProperty(propertyDTO,propertyId);
        ResponseEntity<PropertyDTO> responseEntity=new ResponseEntity<>(propertyDTO1,HttpStatus.CREATED);
        return responseEntity;
    }

    @PatchMapping("updated/{propertyId}")
    public ResponseEntity<PropertyDTO> updateDescription(@RequestBody PropertyDTO propertyDTO,@PathVariable Long propertyId)
    {
        PropertyDTO propertyDTO1=propertyService.updateDescription(propertyDTO,propertyId);
        ResponseEntity<PropertyDTO> propertyDTOResponseEntity=new ResponseEntity<>(propertyDTO1,HttpStatus.CREATED);
        return propertyDTOResponseEntity;
    }

    @DeleteMapping("{propertyId}")
    public  ResponseEntity<Void> delete(@PathVariable Long propertyId)
    {
        propertyService.delete(propertyId);
        ResponseEntity<Void> responseEntity=new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        return responseEntity;
    }
}
