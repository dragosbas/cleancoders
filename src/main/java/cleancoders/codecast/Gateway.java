package cleancoders.codecast;

import java.util.List;

public interface Gateway {
    List<Codecast> findAlLCodecastsSortedChronoligically();
    void delete(Codecast codecast);
    Codecast save(Codecast codecast);
    User save(User user);
    void save (License license);
    User findUser(String username);
    Codecast findCodecastByTitle(String codecastTitle);
    List<License> findLicensesForUserAndCodecast(User user, Codecast codecast);
 }
