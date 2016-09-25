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
            try {
                OntwikkelBedrijf.devMeeting.await();
                OntwikkelBedrijf.devInvitation.release(3);
                haveMeeting();
            }   catch (InterruptedException ie){
                System.out.println("main thread interupted, leader woken up, checking the situation:");

                if (OntwikkelBedrijf.arrivedAtCompany.tryAcquire()){
                    try{
                        if (OntwikkelBedrijf.ontwikkelaarsInMeeting < 1){
                            OntwikkelBedrijf.readyForUserMeeting.acquire();
                        }
                        OntwikkelBedrijf.devInvitation.release();
                        OntwikkelBedrijf.meetingInvitation.release(OntwikkelBedrijf.amtOfUsersArrived);
                        haveMeeting();
                    }   catch (InterruptedException i){
                        i.printStackTrace();
                    }
                } else if (OntwikkelBedrijf.probleem.tryAcquire()) {
                    System.out.println("user has a bug, sending an invite & going back to sleep until he has arrived zzz..");
                    OntwikkelBedrijf.meetingInvitation.release();
                }
                OntwikkelBedrijf.projectLeidersTijd.release();
                System.out.println("projectleiders tijd released");
            }
        }
    }


    private void haveMeeting(){
        try {
            OntwikkelBedrijf.leiderInOverleg = true;
            System.out.println("initializing meeting");
            Thread.sleep(10000);
            System.out.println("meeting finished succesfully");
            OntwikkelBedrijf.leiderInOverleg = false;
            OntwikkelBedrijf.ontwikkelaarsInMeeting = 0;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
