package ro.ne8.oauth2.authorizationserver.service;


import ro.ne8.oauth2.authorizationserver.entities.UserEntity;

import java.util.List;

public interface UserService {
    void save(UserEntity userEntity);

    List<UserEntity> findAll();

    UserEntity getByEmail(String email);

    UserEntity getById(Long id);

    void delete(UserEntity userEntity);

    UserEntity findByNickname(String nickname);

}
