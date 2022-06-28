package cleancoders.codecast;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


import static cleancoders.codecast.License.LicenseType.*;
import static org.junit.jupiter.api.Assertions.*;

public class PresentCodecastUseCaseTest {
    private User user;
    private Codecast codecast;
    private PresentCodecastUseCase useCase;

    @BeforeEach
    public void setUp() {
        Context.gateway = new MockGateway();
        user = Context.gateway.save(new User("User"));
        codecast = Context.gateway.save(new Codecast());
        useCase = new PresentCodecastUseCase();
    }

    @Test
    public void userWithoutViewLicense_cannotViewCodecast() throws Exception {
        assertFalse(useCase.isLicensedFor(VIEWING, user, codecast));
    }

    @Test
    public void userWithViewLicense_canViewCodecast() throws Exception {
        License viewLicense = new License(VIEWING, user, codecast);
        Context.gateway.save(viewLicense);
        assertTrue(useCase.isLicensedFor(VIEWING, user, codecast));
    }

    @Test
    public void userWithoutViewLicense_cannotViewOtherUserCodecast() throws Exception {
        User otherUser = Context.gateway.save(new User("otherUser"));

        License viewLicense = new License(VIEWING, user, codecast);
        Context.gateway.save(viewLicense);
         assertFalse(useCase.isLicensedFor(VIEWING, otherUser, codecast));
    }

    @Test
    public void presentOneCodecast() throws Exception {
        codecast.setTitle("Some Title");
        Date date = new GregorianCalendar(2014, 4, 19).getTime();//lunile incep de la 0 !
        codecast.setPublicationDate(date);
        List<PresentableCodecast> presentableCodecasts = useCase.presentCodecasts(user);
        assertEquals(1, presentableCodecasts.size());
        PresentableCodecast presentableCodecast = presentableCodecasts.get(0);
        assertEquals("Some Title", presentableCodecast.title);
        assertEquals("05/19/2014", presentableCodecast.publicationDate);
    }

    @Test
    public void presentedCodecastIsNotViewableIfNoLicense() throws Exception {
        List<PresentableCodecast> presentableCodecasts = useCase.presentCodecasts(user);
        PresentableCodecast presentableCodecast = presentableCodecasts.get(0);
        assertFalse(presentableCodecast.isViewable);
    }

    @Test
    public void presentedCodecastIsViewableIfLicenseExists() throws Exception {
        Context.gateway.save(new License(VIEWING,user, codecast));
        List<PresentableCodecast> presentableCodecasts = useCase.presentCodecasts(user);
        PresentableCodecast presentableCodecast = presentableCodecasts.get(0);
        assertTrue(presentableCodecast.isViewable);
        assertFalse(presentableCodecast.isDownlodable);
    }

    @Test
    public void presntedCodecastIsDownlodableIfDownloadLicenseExists() throws Exception {
        License downloadLicense = new License(DOWNLOADING,user, codecast);
        Context.gateway.save(downloadLicense);
        List<PresentableCodecast> presentableCodecasts = useCase.presentCodecasts(user);
        PresentableCodecast presentableCodecast = presentableCodecasts.get(0);
        assertTrue(presentableCodecast.isDownlodable);
    }
}
