/**
 * Created by yketd on 14-9-2016.
 */
public class Ontwikkelaar extends Thread {
    private long workTime = 10000;

    public void run(){
        while (true) {
//        try{
            work();
            meldBeschikbaar();

//        }   catch (InterruptedException ie){
//        }
        }
    }

    private void work(){
        try {
            Thread.sleep(workTime);
        }   catch (InterruptedException ie){
        }

    }
    private void meldBeschikbaar(){
        try {
            if (OntwikkelBedrijf.ontwikkelaarsInMeeting < 3) {
                OntwikkelBedrijf.ontwikkelaarsInMeeting += 1;
                OntwikkelBedrijf.readyForMeeting.acquire();
                if (OntwikkelBedrijf.userMeeting && OntwikkelBedrijf.amtInMeeting == 0){
                    OntwikkelBedrijf.amtInMeeting += 1;
                    OntwikkelBedrijf.meeting.acquire();
                }   else if (!OntwikkelBedrijf.userMeeting){
                    OntwikkelBedrijf.meeting.acquire();
                }


            }
        }   catch(InterruptedException ie){

        }
    }
}
