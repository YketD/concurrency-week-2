import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * Created by yketd on 14-9-2016.
 */
public class OntwikkelBedrijf {
    private static int NUMBER_OF_ONTWIKKELAARS = 6;
    private static int NUMBER_OF_GEBRUIKERS = 10;

    public static Semaphore readyForMeeting;
    public static Semaphore probleem;
    public static Semaphore meetingInvitation;
    public static Semaphore arrivedAtCompany;
    public static Semaphore readyForUserMeeting;
    public static Semaphore increaseDevsWaiting;
    public static Semaphore increaseUsersArrived;
    public static Semaphore devInvitation;

    private static Ontwikkelaar[] ontwikkelaars;
    private  Gebruiker[] gebruikers;
    public static int ontwikkelaarsInMeeting = 0;
    public static int amtOfUsersArrived = 0;
    public static Semaphore canWork;
    public static boolean userMeeting;
    public static boolean leiderInOverleg = false;
    public static Semaphore projectLeidersTijd;
    public static int amtInMeeting;

    static ProjectLeider projectLeider = new ProjectLeider("jaap");

    public static CountDownLatch devMeeting;


    public OntwikkelBedrijf(){
        /* semaphore's voor gebruikers*/
        probleem = new Semaphore(0, true);
        meetingInvitation = new Semaphore(0,true);
        arrivedAtCompany = new Semaphore(0,true);
        readyForUserMeeting = new Semaphore(1, true);
        devInvitation = new Semaphore(0, true);
        increaseUsersArrived = new Semaphore(1,true);
        projectLeidersTijd = new Semaphore(1,true);
        canWork = new Semaphore(0,true);


        readyForMeeting = new Semaphore(0, true);
        increaseDevsWaiting = new Semaphore(1,true);

        devMeeting  = new CountDownLatch(4);

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


        projectLeider.start();
    }
    public static long getRandomTime(){
        return (long) (Math.random() * 20000);
    }
    public static long getRandomTime(long time){
        return (long) (Math.random() * time);
    }

    public static Ontwikkelaar[] getOntwikkelaars() {
        return ontwikkelaars;
    }
}
