package ro.ne8.oauth2.authorizationserver.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.ne8.oauth2.authorizationserver.entities.UserRoleEntity;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRoleEntity, Long> {
    UserRoleEntity findByType(String type);
}
