package com.companyname.propertymanagement.service.propertyServiceImpl;

import com.companyname.propertymanagement.dto.PropertyDTO;
import com.companyname.propertymanagement.entity.PropertyEntity;
import com.companyname.propertymanagement.repository.PropertyRepository;
import com.companyname.propertymanagement.service.PropertyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service     public class PropertyServiceImpl implements PropertyService
{

    @Autowired
    private PropertyRepository propertyRepository;
    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO)
    {
        PropertyEntity pe=new PropertyEntity();
        /*pe.setAddress(propertyDTO.getAddress());
        pe.setDescription(propertyDTO.getDescription());
        pe.setPrice(propertyDTO.getPrice());
        pe.setOwnerName(propertyDTO.getOwnerName());
        pe.setOwnerEmail(propertyDTO.getOwnerEmail());
        pe.setTitle(propertyDTO.getTitle());*/
        BeanUtils.copyProperties(propertyDTO,pe);
        pe=propertyRepository.save(pe);
        BeanUtils.copyProperties(pe,propertyDTO);
        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> getAllProperties()
    {
        List<PropertyEntity> propertyEntities=propertyRepository.findAll();
        List<PropertyDTO> propertyDTOS=new ArrayList<>();
        PropertyDTO propertyDTO=new PropertyDTO();
        for(PropertyEntity pe:propertyEntities)
        {
            BeanUtils.copyProperties(pe,propertyDTO);
            propertyDTOS.add(propertyDTO);
        }
        return propertyDTOS;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId)
    {
        Optional<PropertyEntity>  propertyEntity=propertyRepository.findById(propertyId);
        if(propertyEntity.isPresent())
        {
           PropertyEntity pe= propertyEntity.get();
            pe.setAddress(propertyDTO.getAddress());
            pe.setDescription(propertyDTO.getDescription());
            pe.setPrice(propertyDTO.getPrice());
            pe.setOwnerName(propertyDTO.getOwnerName());
            pe.setOwnerEmail(propertyDTO.getOwnerEmail());
            pe.setTitle(propertyDTO.getTitle());
            pe=propertyRepository.save(pe);
            BeanUtils.copyProperties(pe,propertyDTO);
        }
        return propertyDTO;
    }

    @Override
    public PropertyDTO updateDescription(PropertyDTO propertyDTO, Long propertyId)
    {
        Optional<PropertyEntity> propertyEntity=propertyRepository.findById(propertyId);
        if(propertyEntity.isPresent())
        {
            PropertyEntity propertyEntity1=propertyEntity.get();
           propertyEntity1.setDescription(propertyDTO.getDescription());
           propertyRepository.save(propertyEntity1);
           BeanUtils.copyProperties(propertyEntity1,propertyDTO);

        }
        return propertyDTO;
    }

    @Override
    public void delete(Long propertyId)
    {
        propertyRepository.deleteById(propertyId);
    }
}
