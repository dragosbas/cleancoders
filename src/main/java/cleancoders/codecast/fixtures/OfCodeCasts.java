package cleancoders.codecast.fixtures;

import cleancoders.codecast.PresentCodecastUseCase;
import cleancoders.codecast.PresentableCodecast;
import cleancoders.codecast.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OfCodeCasts {
    private List<Object> list(Object... objects) {
        return Arrays.asList(objects);
    }

    public List<Object> query() {
        User loggedInUser = CodecastPresentation.gateKeeper.getLoggedInUser();
        PresentCodecastUseCase useCase = new PresentCodecastUseCase();
        List<PresentableCodecast> presentableCodecasts = useCase.presentCodecasts(loggedInUser);
        List<Object> queryResponse = new ArrayList<Object>();
        for (PresentableCodecast pcc : presentableCodecasts
        ) {
            queryResponse.add(makeRow(pcc));
        }
        return queryResponse;
    }

    private List<Object> makeRow(PresentableCodecast pc) {
        return list(new Object[]{list("title", pc.title),
                list("publication date",pc.publicationDate),
//                list("picture", pc.picture),
//                list("description", pc.description),
                list("viewable", pc.isViewable ? "+" : "-"),
                list("downlodable", pc.isDownlodable ? "+" : "-")});
    }
}
