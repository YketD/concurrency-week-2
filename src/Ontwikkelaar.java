import jdk.nashorn.internal.ir.ContinueNode;

import java.security.acl.Owner;

/**
 * Created by yketd on 14-9-2016.
 */
public class Ontwikkelaar extends Thread {
    private long workTime = 3000;
    private boolean isWaiting;

    public void run() {
        while (true) {
            work();
            meldBeschikbaar();
        }
    }

    private void work() {
        try {
            Thread.sleep(OntwikkelBedrijf.getRandomTime());
        } catch (InterruptedException ie) {
        }

    }

    private void meldBeschikbaar() {

//        System.out.println("proberen beschikbaar te melden");
        try {
            isWaiting = true;
            OntwikkelBedrijf.increaseDevsWaiting.acquire();
            if (!OntwikkelBedrijf.leiderInOverleg && OntwikkelBedrijf.ontwikkelaarsInMeeting != 3) {
                OntwikkelBedrijf.ontwikkelaarsInMeeting += 1;
                OntwikkelBedrijf.readyForUserMeeting.release();
                System.out.println("ik ben de " + OntwikkelBedrijf.ontwikkelaarsInMeeting + "'e persoon in de wachtrij");
                OntwikkelBedrijf.devMeeting.countDown();
                OntwikkelBedrijf.increaseDevsWaiting.release();

                OntwikkelBedrijf.devInvitation.acquire();
                isWaiting = false;
                    System.out.println("invite acquired, meeting starts..");
                    haveMeeting();
                OntwikkelBedrijf.readyForUserMeeting.acquire();
            } else {
                OntwikkelBedrijf.increaseDevsWaiting.release();
            }
        } catch (InterruptedException ie) {
            System.out.println("i am not needed, going back to work");
        }

    }


    private void haveMeeting() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public boolean isWaiting(){
        return isWaiting;
    }
}
