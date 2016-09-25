import jdk.nashorn.internal.ir.ContinueNode;

import java.security.acl.Owner;

/**
 * Created by yketd on 14-9-2016.
 */
public class Ontwikkelaar extends Thread {
    private long workTime = 3000;

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
            OntwikkelBedrijf.increaseDevsWaiting.acquire();
            if (!OntwikkelBedrijf.leiderInOverleg && OntwikkelBedrijf.ontwikkelaarsInMeeting != 3) {
                OntwikkelBedrijf.ontwikkelaarsInMeeting += 1;
                System.out.println("ik ben de " + OntwikkelBedrijf.ontwikkelaarsInMeeting + "'e persoon in de wachtrij");
                OntwikkelBedrijf.devMeeting.countDown();
                OntwikkelBedrijf.increaseDevsWaiting.release();
                OntwikkelBedrijf.devInvitation.acquire();
                    System.out.println("invite acquired, meeting starts..");
                    haveMeeting();

            } else {
                OntwikkelBedrijf.increaseDevsWaiting.release();
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

    }


    private void haveMeeting() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
