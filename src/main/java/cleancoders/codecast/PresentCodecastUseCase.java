package cleancoders.codecast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static cleancoders.codecast.License.LicenseType.*;

public class PresentCodecastUseCase {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    public List<PresentableCodecast> presentCodecasts(User loggedInUser) {
        ArrayList<PresentableCodecast> presentableCodecasts = new ArrayList<PresentableCodecast>();
        List<Codecast> allCodecasts = Context.gateway.findAlLCodecastsSortedChronoligically();

        for (Codecast codecast : allCodecasts)
            presentableCodecasts.add(formatCodecast(loggedInUser, codecast));

        return presentableCodecasts;
    }

    private PresentableCodecast formatCodecast(User loggedInUser, Codecast codecast) {
        PresentableCodecast cc = new PresentableCodecast();
        cc.title = codecast.getTitle();
        cc.publicationDate = dateFormat.format(codecast.getPublicationDate());
        cc.isViewable = isLicensedFor(VIEWING, loggedInUser, codecast);
        cc.isDownlodable = isLicensedFor(DOWNLOADING, loggedInUser, codecast);
        return cc;
    }


    public boolean isLicensedFor(License.LicenseType viewable, User user, Codecast codecast) {
        List<License> licenses = Context.gateway.findLicensesForUserAndCodecast(user, codecast);
       for (License l : licenses) {
            if (l.getType() == viewable && l.getUser()==user) return true;
        }
        return false;
    }
}

