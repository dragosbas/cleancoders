package cleancoders.codecast.doubles;

import cleancoders.codecast.GatewayUtilities;
import cleancoders.codecast.User;
import cleancoders.codecast.interfaces.UserGateway;

public class InMemoryUserGateway extends GatewayUtilities<User> implements UserGateway {
    public User findUserByName(String username) {
        for (User user : getEntities()) {
            if (user.getUserName().equals(username)) return user;
        }
        return null;
    }

}

