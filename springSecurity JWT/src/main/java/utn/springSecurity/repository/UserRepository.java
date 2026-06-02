package utn.springSecurity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import utn.springSecurity.model.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findUserEntityByUsername(String username);

}
