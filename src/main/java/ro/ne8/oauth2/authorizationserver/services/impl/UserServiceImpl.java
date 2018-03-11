package ro.ne8.oauth2.authorizationserver.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.ne8.oauth2.authorizationserver.entities.UserEntity;
import ro.ne8.oauth2.authorizationserver.entities.UserRoleEntity;
import ro.ne8.oauth2.authorizationserver.repositories.UserRepository;
import ro.ne8.oauth2.authorizationserver.repositories.UserRoleRepository;
import ro.ne8.oauth2.authorizationserver.services.UserService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(final UserEntity userEntity) {
        userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword()));
        final Set<UserRoleEntity> userRoleEntities = this.createAndSaveDefaultRolesForUser();
        userEntity.setUserRoles(userRoleEntities);
        this.userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> findAll() {
        final Iterable<UserEntity> userEntities = this.userRepository.findAll();
        final List<UserEntity> userEntityList = new ArrayList<>();

        for (final UserEntity userEntity : userEntities) {
            userEntityList.add(userEntity);
        }
        return userEntityList;
    }


    @Override
    public UserEntity getByEmail(final String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public UserEntity getById(final Long id) {
        return this.userRepository.findOne(id);
    }

    @Override
    public void delete(final UserEntity userEntity) {
        this.userRepository.delete(userEntity);
    }

    @Override
    public UserEntity findByUsername(final String username) {
        return this.userRepository.findByUsername(username);
    }

    private Set<UserRoleEntity> createAndSaveDefaultRolesForUser() {
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        final UserRoleEntity existingEntity = this.userRoleRepository.findByType(userRoleEntity.getType());
        if (existingEntity == null) {
            this.userRoleRepository.save(userRoleEntity);
        } else {
            userRoleEntity = existingEntity;
        }
        final Set<UserRoleEntity> userRoleEntitySet = new HashSet<>();
        userRoleEntitySet.add(userRoleEntity);
        return userRoleEntitySet;
    }

}
