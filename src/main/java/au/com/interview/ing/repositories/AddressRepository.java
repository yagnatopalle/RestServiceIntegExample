package au.com.interview.ing.repositories;

import org.springframework.data.repository.CrudRepository;

import au.com.interview.ing.entity.AddressEntity;

public interface AddressRepository extends CrudRepository<AddressEntity, Long> {

}
