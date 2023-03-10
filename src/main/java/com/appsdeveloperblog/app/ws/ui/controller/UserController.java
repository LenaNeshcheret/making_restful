package com.appsdeveloperblog.app.ws.ui.controller;

import com.appsdeveloperblog.app.ws.exception.hadler.RecordExistsRuntimeException;
import com.appsdeveloperblog.app.ws.service.AddressesService;
import com.appsdeveloperblog.app.ws.service.UserService;
import com.appsdeveloperblog.app.ws.service.UserServiceException;
import com.appsdeveloperblog.app.ws.shared.dto.AddressDTO;
import com.appsdeveloperblog.app.ws.shared.dto.UserDto;
import com.appsdeveloperblog.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.response.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    public static final ModelMapper MODEL_MAPPER = new ModelMapper();
    @Autowired
    UserService userService;

    @Autowired
    AddressesService addressesService;

    @GetMapping(path = "/{id}")
    public UserRest getUsers(@PathVariable String id) {
        UserRest returnValue = new UserRest();

        UserDto userDto = userService.getUserByUserId(id);
        BeanUtils.copyProperties(userDto, returnValue);

        return returnValue;
    }

    @PostMapping
    @ExceptionHandler({RecordExistsRuntimeException.class})
    public UserRest createUsers(@RequestBody UserDetailsRequestModel userDetails) {

        UserRest returnValue = new UserRest();

        if (userDetails.getFirstName().isEmpty())
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = MODEL_MAPPER.map(userDetails, UserDto.class);

        UserDto createUser = userService.createUser(userDto);
//        BeanUtils.copyProperties(createUser, returnValue);
        returnValue = MODEL_MAPPER.map(createUser, UserRest.class);
        return returnValue;
    }

    @PutMapping(path = "/{id}")
    public UserRest updateUsers(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) {

        UserRest returnValue = new UserRest();

        if (userDetails.getFirstName().isEmpty())
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto updateUser = userService.updateUser(id, userDto);
        BeanUtils.copyProperties(updateUser, returnValue);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteUsers(@PathVariable String id) {

        OperationStatusModel returnValue = new OperationStatusModel();

        returnValue.setOperationName(RequestOperationName.DELETE.name());
        userService.deleteUser(id);
        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());

        return returnValue;
    }

    @GetMapping
    public List<UserRest> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "limit", defaultValue = "25") int limit) {
        List<UserRest> returnValue = new ArrayList<>();

        List<UserDto> users = userService.getListUsers(page, limit);

        for (UserDto userDto : users) {
            UserRest userModel = new UserRest();
            BeanUtils.copyProperties(userDto, userModel);
            returnValue.add(userModel);
        }
        return returnValue;
    }

//    @GetMapping(path = "/{id}/addresses")
//    public List<AddressesRest> getUsersAddresses(@PathVariable String id) {
//        List<AddressesRest> returnValue = new ArrayList<>();
//
//        List<AddressDTO> addressesDto = userService.getAddresses(id);
//
//        if (addressesDto != null && !addressesDto.isEmpty()) {
//            java.lang.reflect.Type listType =
//                    new TypeToken<List<AddressesRest>>() {
//                    }.getType();
//            returnValue = MODEL_MAPPER.map(addressesDto, listType);
//        }
//
//        return returnValue;
//    }

    @GetMapping(path = "/{id}/addresses")
    public List<AddressesRest> getUsersAddresses(@PathVariable String id) {
        List<AddressesRest> returnValue = new ArrayList<>();

        List<AddressDTO> addressesDto = addressesService.getAddresses(id);

        if (addressesDto != null && !addressesDto.isEmpty()) {
            java.lang.reflect.Type listType =
                    new TypeToken<List<AddressesRest>>() {
                    }.getType();
            returnValue = MODEL_MAPPER.map(addressesDto, listType);
        }

        return returnValue;
    }

    @GetMapping(path = "/{userId}/addresses/{addressesId}")
    public AddressesRest getUserAddress(@PathVariable String addressesId) {
        AddressesRest returnValue = new AddressesRest();

        AddressDTO addressDto = addressesService.getAddress(addressesId);

        returnValue = MODEL_MAPPER.map(addressDto, AddressesRest.class);

        return returnValue;
    }
}
