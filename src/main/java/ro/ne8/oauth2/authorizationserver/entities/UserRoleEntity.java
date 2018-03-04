package ro.ne8.oauth2.authorizationserver.entities;

import ro.ne8.oauth2.authorizationserver.entities.enums.UserRoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "roles", schema = "users")
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type", length = 64, unique = true, nullable = false)
    private final String type = UserRoleEnum.USER.getUserRole();

    public String getType() {
        return this.type;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }


}
