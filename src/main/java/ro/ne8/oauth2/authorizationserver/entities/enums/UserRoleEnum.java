package ro.ne8.oauth2.authorizationserver.entities.enums;

/**
 * Created by neop on 1/14/17.
 */
public enum UserRoleEnum {

    USER("USER"),
    DBA("DBA"),
    ADMIN("ADMIN");

    String userProfileType;

    private UserRoleEnum(final String userProfileType) {
        this.userProfileType = userProfileType;
    }

    public String getUserRole() {
        return this.userProfileType;
    }

}
