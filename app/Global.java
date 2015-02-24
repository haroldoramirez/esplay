import play.Application;
import play.GlobalSettings;
import play.libs.F.Promise;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;

import static play.mvc.Results.notFound;

public class Global extends GlobalSettings {

    @Override
    public Promise<Result> onHandlerNotFound(RequestHeader request) {
        return Promise.<Result> pure(notFound(views.html.notFound.render(request.uri())));
    }

    @Override
    public void onStart(Application app) {
        System.out.println("Play Application Start");
    }

    @Override
    public void onStop(Application application) {
        System.out.println("Play Application Stop");
    }
}
