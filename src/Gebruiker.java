/**
 * Created by yketd on 14-9-2016.
 */
public class Gebruiker extends Thread {

    public void run(){
        while(true) {
            gebruiken();
            meldProbleem();
            getInvitation();
            driveToCompany();
            waitForMeeting();

        }
    }

    private void gebruiken() {
        try {
//            System.out.println("gebruiker gebruikt app..");
            Thread.sleep(OntwikkelBedrijf.getRandomTime(100000));
        } catch (InterruptedException ie) {
        }
    }

    public void meldProbleem(){
        System.out.println("bug! notifying projectleider");
        try {
            OntwikkelBedrijf.projectLeidersTijd.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }OntwikkelBedrijf.probleem.release();
        OntwikkelBedrijf.projectLeider.interrupt();
    }

    public void getInvitation(){
        try {
            OntwikkelBedrijf.meetingInvitation.acquire();
            System.out.println("meeting invitation acquired");
        } catch (InterruptedException ie){}
    }

    public void driveToCompany(){
        try {
            System.out.println("driving to company..");
            Thread.sleep(1000);

            OntwikkelBedrijf.projectLeidersTijd.acquire();
            System.out.println("im here!");
            OntwikkelBedrijf.increaseUsersArrived.acquire();
            OntwikkelBedrijf.amtOfUsersArrived +=1;
            OntwikkelBedrijf.increaseUsersArrived.release();
            OntwikkelBedrijf.arrivedAtCompany.release();

            OntwikkelBedrijf.projectLeider.interrupt();

        } catch (InterruptedException ie){

        }
    }

    public void waitForMeeting(){
        try {
            OntwikkelBedrijf.meetingInvitation.acquire();
            haveMeeting();
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
