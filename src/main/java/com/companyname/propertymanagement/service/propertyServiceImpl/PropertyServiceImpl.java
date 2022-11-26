package com.companyname.propertymanagement.service.propertyServiceImpl;

import com.companyname.propertymanagement.dto.PropertyDTO;
import com.companyname.propertymanagement.entity.PropertyEntity;
import com.companyname.propertymanagement.entity.UserEntity;
import com.companyname.propertymanagement.exception.BusinessException;
import com.companyname.propertymanagement.exception.ErrorModel;
import com.companyname.propertymanagement.repository.PropertyRepository;
import com.companyname.propertymanagement.repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;
    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO)
    {
       Optional<UserEntity> opet= userRepository.findById(propertyDTO.getUserId());
       if(opet.isPresent()) {
           PropertyEntity pe = new PropertyEntity();
           BeanUtils.copyProperties(propertyDTO, pe);
           pe.setUserEntity(opet.get());
           pe = propertyRepository.save(pe);
           BeanUtils.copyProperties(pe, propertyDTO);
       }else {
           List<ErrorModel> errorModels=new ArrayList<>();
           ErrorModel errorModel=new ErrorModel();
           errorModel.setCode("USER_ID_NOT_VALID");
           errorModel.setMessage("NOT_PRESENT");
           errorModels.add(errorModel);
           throw new BusinessException(errorModels);
       }
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
    public List<PropertyDTO> getPropertiesById(Long userId)
    {
        List<PropertyEntity> propertyEntities =propertyRepository.findAllByUserEntityId(userId);
        List<PropertyDTO> propertyDTOS=new ArrayList<>();
        PropertyDTO propertyDTO=new PropertyDTO();
        for(PropertyEntity pe:propertyEntities)
        {
            BeanUtils.copyProperties(pe,propertyDTO);
            propertyDTOS.add(propertyDTO);

        }
        return  propertyDTOS;
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
