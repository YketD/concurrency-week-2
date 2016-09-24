/**
 * Created by yketd on 14-9-2016.
 */
public class Gebruiker extends Thread {

    public void run(){
        while(true) {
            gebruiken();
//            meldProbleem();
//            getInvitation();
//            driveToCompany();
//            startMeeting();

        }
    }

    private void gebruiken() {
        try {
            System.out.println("gebruiker gebruikt app..");

            Thread.sleep(100000);
        } catch (InterruptedException ie) {
        }
    }

    public void meldProbleem(){
        OntwikkelBedrijf.projectLeider.interrupt();
        OntwikkelBedrijf.probleem.release();
    }

    public void getInvitation(){
        try {
            OntwikkelBedrijf.meetingInvitation.acquire();
            System.out.println("meeting invitation acquired");
        } catch (InterruptedException ie){}
    }

    public void driveToCompany(){
        try {
            Thread.sleep(10000);
            OntwikkelBedrijf.arrivedAtCompany.release();
        } catch (InterruptedException ie){

        }
    }

    public void startMeeting(){
        try {
            System.out.println("user getting into meeting");
            OntwikkelBedrijf.readyForUserMeeting.acquire();
            System.out.println("user in meeting");
        } catch (InterruptedException ie){}
    }

    private void haveMeeting(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
