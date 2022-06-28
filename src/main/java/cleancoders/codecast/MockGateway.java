package cleancoders.codecast;

import java.util.*;

public class MockGateway implements Gateway {
    private List<Codecast> codecasts;
    private List<User> users;
    private ArrayList<License> licenses;

    public MockGateway() {
        codecasts = new ArrayList<Codecast>();
        users = new ArrayList<User>();
        licenses = new ArrayList<License>();
    }

    @Override
    public List<Codecast> findAlLCodecastsSortedChronoligically() {
        List<Codecast> sortedCodecasts = new ArrayList<Codecast>(codecasts);
        Collections.sort(sortedCodecasts, new Comparator<Codecast>() {
            @Override
            public int compare(Codecast o1, Codecast o2) {
                return o1.getPublicationDate().compareTo(o2.getPublicationDate());
            }
        });
        return sortedCodecasts;
    }

    @Override
    public void delete(Codecast codecast) {
        codecasts.remove(codecast);
    }

    @Override
    public Codecast save(Codecast codecast) {
        codecasts.add(((Codecast) establishId(codecast)));
        return codecast;
    }

    @Override
    public User save(User user) {
        users.add((User) establishId(user));
        return user;
    }

    private Entity establishId(Entity entity) {
        if (entity.getId() == null) entity.setId(UUID.randomUUID().toString());
        return entity;
    }

    @Override
    public void save(License license) {
        licenses.add(license);
    }

    @Override
    public User findUser(String username) {
        for (User user : users) {
            if (user.getUserName() == username) return user;
        }
        return null;
    }

    @Override
    public Codecast findCodecastByTitle(String codecastTitle) {
        for (Codecast codecast : codecasts)
            if (codecast.getTitle().equals(codecastTitle)) return codecast;
        return null;
    }

    @Override
    public List<License> findLicensesForUserAndCodecast(User user, Codecast codecast) {
        List<License> results = new ArrayList<License>();
        for (License license : licenses) {
            if (license.getUser().isSame(user) && license.getCodecast().isSame(codecast)) results.add(license);
        }
        return results;

    }

}
