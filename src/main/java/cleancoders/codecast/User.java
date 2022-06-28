package cleancoders.codecast;

import java.util.Objects;

public class User extends Entity {
    private String userName;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User(String userName) {
        this.userName = userName;
    }
}
