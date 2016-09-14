import java.util.concurrent.Semaphore;

/**
 * Created by yketd on 14-9-2016.
 */
public class OntwikkelBedrijf {
    private static int NUMBER_OF_ONTWIKKELAARS = 6;
    private static int NUMBER_OF_GEBRUIKERS = 10;
    public  static Semaphore probleem;
    private Ontwikkelaar[] ontwikkelaars;
    private Gebruiker[] gebruikers;

    public OntwikkelBedrijf(){
        probleem = new Semaphore(0, true);

        ontwikkelaars = new Ontwikkelaar[NUMBER_OF_ONTWIKKELAARS];
        for (int i = 0; i < NUMBER_OF_ONTWIKKELAARS; i++){
            ontwikkelaars[i] = new Ontwikkelaar();
            ontwikkelaars[i].start();
        }

        gebruikers = new Gebruiker[NUMBER_OF_GEBRUIKERS];
        for (int i = 0; i < NUMBER_OF_GEBRUIKERS; i++){
            gebruikers[i] = new Gebruiker();
            gebruikers[i].start();
        }

        ProjectLeider projectLeider = new ProjectLeider("jaap");
        projectLeider.start();

    }
}
