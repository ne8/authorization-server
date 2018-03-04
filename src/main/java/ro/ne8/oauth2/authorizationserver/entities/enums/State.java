package ro.ne8.oauth2.authorizationserver.entities.enums;

/**
 * Created by neop on 1/14/17.
 */
public enum State {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    DELETED("Deleted"),
    LOCKED("Locked");

    private final String state;

    private State(final String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    @Override
    public String toString() {
        return this.state;
    }

    public String getName() {
        return this.name();
    }

}
