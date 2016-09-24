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
            Thread.sleep(workTime);
        } catch (InterruptedException ie) {
        }

    }

    private void meldBeschikbaar() {
        try {

            if (!OntwikkelBedrijf.leiderInOverleg && OntwikkelBedrijf.ontwikkelaarsInMeeting != 3) {
                OntwikkelBedrijf.increaseDevsWaiting.acquire();
                OntwikkelBedrijf.ontwikkelaarsInMeeting += 1;
                OntwikkelBedrijf.increaseDevsWaiting.release();

                System.out.println("ik ben de " + OntwikkelBedrijf.ontwikkelaarsInMeeting + "'e persoon in de wachtrij");

                if (OntwikkelBedrijf.ontwikkelaarsInMeeting == 3) {
                    System.out.println("enough devs are in to start the meeting, projectleider is being woken up");
                    OntwikkelBedrijf.projectLeider.interrupt();
                    OntwikkelBedrijf.devInvitation.acquire();
                    System.out.println("invite acquired, meeting starts..");
                    haveMeeting();
                }else {
                }
                }

            OntwikkelBedrijf.increaseDevsWaiting.release();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

    }




    private void haveMeeting(){
        try {
            Thread.sleep(10000);
            System.out.println("meeting finished succesfully");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
