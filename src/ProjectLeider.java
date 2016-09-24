import sun.awt.windows.ThemeReader;

/**
 * Created by yketd on 14-9-2016.
 */
public class ProjectLeider extends Thread {

    String naam;

    public ProjectLeider(String naam){
        this.naam = naam;
    }
    public void run(){
        while (true){
            nap();
        }
    }

    public void nap(){
            try{
                System.out.println("snoozing ...");
                Thread.sleep(10000);
            } catch (InterruptedException ie){
                System.out.println("main thread interupted, leader woken up, checking the situation:");

                if (OntwikkelBedrijf.probleem.tryAcquire()) {
                    try {
                        OntwikkelBedrijf.probleem.acquire();
                        OntwikkelBedrijf.meetingInvitation.release();
                        OntwikkelBedrijf.arrivedAtCompany.acquire();
                        if (OntwikkelBedrijf.ontwikkelaarsInMeeting > 0) {
                            OntwikkelBedrijf.devInvitation.release();
                        }
                        else{
                            OntwikkelBedrijf.devInvitation.release(3);
                            haveMeeting();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }   else if (OntwikkelBedrijf.ontwikkelaarsInMeeting == 3){
                    System.out.println("devs are ready, sending 3 invites");
                    OntwikkelBedrijf.devInvitation.release(3);
                    haveMeeting();
                }

            }
        }


    private void haveMeeting(){
        try {
            OntwikkelBedrijf.leiderInOverleg = true;
            Thread.sleep(10000);
            System.out.println("meeting finished succesfully");
            OntwikkelBedrijf.leiderInOverleg = false;
            OntwikkelBedrijf.ontwikkelaarsInMeeting = 0;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
