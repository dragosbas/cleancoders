package cleancoders.codecast;

import cleancoders.codecast.doubles.InMemoryLicenseGateway;
import cleancoders.codecast.doubles.InMemoryUserGateway;

public class TestSetup {
    public static void setupContext() {
        Context.userGateway = new InMemoryUserGateway();
        Context.licenseGateway = new InMemoryLicenseGateway();
//        Context.codecastGateway = new InMemoryCodecastGateway();
//        Context.gateKeeper = new GateKeeper();
    }
}
