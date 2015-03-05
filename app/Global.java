import play.Application;
import play.GlobalSettings;

public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
        System.out.println("Play Application Start");
    }

    @Override
    public void onStop(Application application) {
        System.out.println("Play Application Stop");
    }
}
