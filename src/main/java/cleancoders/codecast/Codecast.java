package cleancoders.codecast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Codecast extends Entity{
    private String title;
    private Date publicationDate=new Date();
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public boolean isSame(Codecast codecast) {
        return id != null || Objects.equals(id, codecast.getId());
    }
}
