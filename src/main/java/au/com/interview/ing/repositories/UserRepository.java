package au.com.interview.ing.repositories;

import org.springframework.data.repository.CrudRepository;

import au.com.interview.ing.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
