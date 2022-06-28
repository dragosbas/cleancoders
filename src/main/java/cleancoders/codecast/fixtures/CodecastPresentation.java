package cleancoders.codecast.fixtures;

import cleancoders.codecast.*;

import java.util.ArrayList;
import java.util.List;

import static cleancoders.codecast.License.LicenseType.*;


public class CodecastPresentation {

    private PresentCodecastUseCase useCase = new PresentCodecastUseCase();
    public static GateKeeper gateKeeper = new GateKeeper();

    public CodecastPresentation() {
        Context.gateway = new MockGateway();
    }

    public boolean addUser(String username) {
        Context.gateway.save(new User(username));
        return true;
    }

    public boolean loginUser(String username) {
        User user = Context.gateway.findUser(username);
        if (user != null) {
            gateKeeper.setLoggedInUser(user);
            return true;
        } else return false;
    }

    public boolean createLicenseForViewing(String username, String codecastTitle) {
        User user = Context.gateway.findUser(username);
        Codecast codecast = Context.gateway.findCodecastByTitle(codecastTitle);
        License license = new License(VIEWING, user, codecast);
        Context.gateway.save(license);
        return useCase.isLicensedFor(VIEWING, user, codecast);
    }

    public boolean createLicenseForDownloading(String username, String codecastTitle) {
        User user = Context.gateway.findUser(username);
        Codecast codecast = Context.gateway.findCodecastByTitle(codecastTitle);
        License license = new License(DOWNLOADING, user, codecast);
        Context.gateway.save(license);
        return useCase.isLicensedFor(DOWNLOADING, user, codecast);
    }

    ;

    public String presentationUser() {
        return gateKeeper.getLoggedInUser().getUserName();
    }

    public boolean clearCodecasts() {
        List<Codecast> codecasts = Context.gateway.findAlLCodecastsSortedChronoligically();
        for (Codecast codecast : new ArrayList<Codecast>(codecasts)) {
            Context.gateway.delete(codecast);
        }
        return Context.gateway.findAlLCodecastsSortedChronoligically().size() == 0;
    }

    public int countOfCodecastsPresented() {
        List<PresentableCodecast> presentations = useCase.presentCodecasts(gateKeeper.getLoggedInUser());
        return presentations.size();
    }

}
