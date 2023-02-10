package com.appsdeveloperblog.app.ws.service.impl;

import com.appsdeveloperblog.app.ws.io.entity.AddressEntity;
import com.appsdeveloperblog.app.ws.io.repositories.AddressesRepository;
import com.appsdeveloperblog.app.ws.io.repositories.UserRepository;
import com.appsdeveloperblog.app.ws.service.AddressesService;
import com.appsdeveloperblog.app.ws.shared.dto.AddressDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressesServiceImpl implements AddressesService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressesRepository addressesRepository;

    @Override
    public List<AddressDTO> getAddresses(String userId) {
        List<AddressDTO> returnValue = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

//        UserEntity userEntity = userRepository.findByUserId(userId);
//        if (userEntity == null) {
//            return returnValue;
//        }
//        List<AddressEntity> addressesFromUserInternally = userEntity.getAddresses();
//        List <AddressEntity> addressEntities = addressesRepository.findAllByUserDetails(userEntity);
//        if(addressEntities.equals(addressesFromUserInternally)){
//            System.out.println("it is the same");
//        }
//        for (AddressEntity addressEntity : addressEntities){
//            returnValue.add(modelMapper.map(addressEntity, AddressDTO.class));
//        }

//        UserDto userDto = new UserDto();
//        userDto.setUserId(userId);
//        //map userDto -> userEntity
//        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
//        userRepository.save(userEntity);

        List<AddressEntity> addressEntityList = addressesRepository.findAllByUserDetails_UserId(userId);

        if (addressEntityList != null && !addressEntityList.isEmpty()) {
            java.lang.reflect.Type listType =
                    new TypeToken<List<AddressDTO>>() {
                    }.getType();
            returnValue = modelMapper.map(addressEntityList, listType);
        }
        return returnValue;
    }

    @Override
    public AddressDTO getAddress(String addressesId) {
        AddressDTO returnValue = new AddressDTO();
        AddressEntity addressEntity = addressesRepository.getAddressEntitiesByAddressId(addressesId);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(addressEntity, AddressDTO.class);
        return returnValue;
    }
}
