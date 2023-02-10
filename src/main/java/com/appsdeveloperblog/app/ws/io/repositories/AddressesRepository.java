package com.appsdeveloperblog.app.ws.io.repositories;

import com.appsdeveloperblog.app.ws.io.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressesRepository extends CrudRepository<AddressEntity, Long> {
    List <AddressEntity> findAllByUserDetails_UserId(String userId);
    AddressEntity getAddressEntitiesByAddressId(String addressId);

}
