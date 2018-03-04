package ro.ne8.oauth2.authorizationserver.facades.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ne8.oauth2.authorizationserver.dto.UserDTO;
import ro.ne8.oauth2.authorizationserver.entities.UserEntity;
import ro.ne8.oauth2.authorizationserver.facades.UserFacade;
import ro.ne8.oauth2.authorizationserver.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(final UserDTO userDTO) {
        this.userService.save(this.modelMapper.map(userDTO, UserEntity.class));
    }

    @Override
    public List<UserDTO> findAll() {
        final List<UserEntity> userEntityList = this.userService.findAll();
        final List<UserDTO> userDTOList = new ArrayList<>();

        for (final UserEntity userEntity : userEntityList) {
            userDTOList.add(this.modelMapper.map(userEntity, UserDTO.class));
        }

        return userDTOList;
    }

    @Override
    public UserDTO getByEmail(final String email) {
        final UserEntity userEntity = this.userService.getByEmail(email);
        if (userEntity != null) {
            return this.modelMapper.map(userEntity, UserDTO.class);
        }
        return null;
    }

    @Override
    public UserDTO getById(final Long id) {
        final UserEntity userEntity = this.userService.getById(id);
        if (userEntity != null) {
            return this.modelMapper.map(userEntity, UserDTO.class);
        }
        return null;
    }

    @Override
    public void delete(final UserDTO userDTO) {
        this.userService.delete(this.modelMapper.map(userDTO, UserEntity.class));
    }
}
