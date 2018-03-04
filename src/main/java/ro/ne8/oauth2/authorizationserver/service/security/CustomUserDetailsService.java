package ro.ne8.oauth2.authorizationserver.service.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ne8.oauth2.authorizationserver.entities.UserEntity;
import ro.ne8.oauth2.authorizationserver.entities.UserRoleEntity;
import ro.ne8.oauth2.authorizationserver.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final static Logger LOGGER = Logger.getLogger(CustomUserDetailsService.class);
    private static final String STATE_ACTIVE = "Active";
    public static final String ROLE_PREFIX_REQUIRED_BY_SPRING_SECURITY_CONTEXT = "ROLE_";

    @Autowired
    private UserService userService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String nickname) throws UsernameNotFoundException {
        LOGGER.debug("trying to fetch user with nickname:" + nickname);
        final UserEntity userEntity = this.userService.findByNickname(nickname);
        if (userEntity == null) {
            LOGGER.error("user not found");
            throw new UsernameNotFoundException("User not found");
        }
        final User springSecurityUser = new User(userEntity.getNickname(), userEntity.getPassword(),
                userEntity.getState().equals(STATE_ACTIVE), userEntity.isAccountNonExpired(),
                userEntity.isCredentialsNonExpired(), userEntity.isAccountNonLocked(), this.getGrantedAuthorities(userEntity));

        return springSecurityUser;
    }

    private List<GrantedAuthority> getGrantedAuthorities(final UserEntity userEntity) {
        final List<GrantedAuthority> authorities = new ArrayList<>();

        for (final UserRoleEntity userRoleType : userEntity.getUserRoles()) {
            authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX_REQUIRED_BY_SPRING_SECURITY_CONTEXT + userRoleType.getType()));
        }
        return authorities;
    }
}
