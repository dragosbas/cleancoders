package cleancoders.codecast.fixtures;

import cleancoders.codecast.Codecast;
import cleancoders.codecast.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GivenCodecasts {
    private String title;
    private String publicationDate;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/d/yyyy");


    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void execute() throws ParseException {
        Codecast codecast = new Codecast();
        codecast.setTitle(title);
        codecast.setPublicationDate(dateFormat.parse(publicationDate));
        Context.gateway.save(codecast);
    }
}
