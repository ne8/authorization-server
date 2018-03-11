package ro.ne8.oauth2.authorizationserver.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.ne8.oauth2.authorizationserver.entities.UserEntity;

/**
 * Created by neop on 09.04.2017.
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);


    UserEntity findByUsername(String username);
}
