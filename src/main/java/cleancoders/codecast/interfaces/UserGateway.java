package cleancoders.codecast.interfaces;

import cleancoders.codecast.User;

public interface UserGateway {
    User save(User user);
    User findUserByName(String username);
}
