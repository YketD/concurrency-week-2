import java.util.concurrent.Semaphore;

/**
 * Created by yketd on 14-9-2016.
 */
public class OntwikkelBedrijf {
    private static int NUMBER_OF_ONTWIKKELAARS = 6;
    private static int NUMBER_OF_GEBRUIKERS = 1;
    public static Semaphore readyForMeeting;
    public  static Semaphore probleem;
    public static Semaphore meetingInvitation;
    public static Semaphore arrivedAtCompany;
    public static Semaphore readyForUserMeeting;
    public static Semaphore devMeeting;
    public static Semaphore meeting;
    private Ontwikkelaar[] ontwikkelaars;
    private Gebruiker[] gebruikers;
    public static int ontwikkelaarsInMeeting = 0;
    public static boolean userMeeting;
    public static int amtInMeeting;


    public OntwikkelBedrijf(){
        probleem = new Semaphore(0, true);
        meetingInvitation = new Semaphore(0,true);
        arrivedAtCompany = new Semaphore(0,true);
        readyForUserMeeting = new Semaphore(1, true);
        readyForMeeting = new Semaphore(0, true);
        devMeeting = new Semaphore(0, true);

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
