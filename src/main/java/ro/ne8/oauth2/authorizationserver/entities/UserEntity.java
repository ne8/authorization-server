package ro.ne8.oauth2.authorizationserver.entities;


import ro.ne8.oauth2.authorizationserver.entities.enums.State;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "users", schema = "users")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "state", nullable = false)
    private final String state = State.ACTIVE.getState();

    @Column(name = "creation_date", nullable = false)
    private final Date creationDate = new Date();

    @Column(name = "is_account_non_expired", nullable = false)
    private final boolean isAccountNonExpired = true;

    @Column(name = "is_credentials_non_expired", nullable = false)
    private final boolean isCredentialsNonExpired = true;

    @Column(name = "is_account_non_locked")
    private final boolean accountNonLocked = true;


    //TODO:  improve so you don't have duplicates within the database
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(schema = "users", name = "user_user_profile",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_profile_id")}
    )
    private Set<UserRoleEntity> userRoles;


    public void setUserRoles(final Set<UserRoleEntity> userRoles) {
        this.userRoles = userRoles;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getState() {
        return this.state;
    }


    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    public Set<UserRoleEntity> getUserRoles() {
        return this.userRoles;
    }


}
