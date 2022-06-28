package cleancoders.codecast;

public class License extends Entity {
    public enum LicenseType {VIEWING, DOWNLOADING}

    private User user;
    private Codecast codecast;
    private LicenseType type;

    public LicenseType getType() {
        return type;
    }

    public License(LicenseType type, User user, Codecast codecast) {
        this.user = user;
        this.codecast = codecast;
        this.type=type;
    }

    public User getUser() {
        return user;
    }

    public Codecast getCodecast() {
        return codecast;
    }

}
