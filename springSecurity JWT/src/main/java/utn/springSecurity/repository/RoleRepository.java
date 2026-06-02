package utn.springSecurity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import utn.springSecurity.model.RoleEntity;
import utn.springSecurity.model.RoleEnum;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByRoleEnum(RoleEnum roleEnum);
}
