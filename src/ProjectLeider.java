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
            try{
                if (OntwikkelBedrijf.ontwikkelaarsInMeeting == 3){
                    System.out.println("start meeting");
                    OntwikkelBedrijf.readyForMeeting.release();
//                if(OntwikkelBedrijf.probleem.tryAcquire()){
//                    System.out.println("probleem acquired.");
//                    System.out.println("sending meeting invitation...");
//                    OntwikkelBedrijf.meetingInvitation.release();
//                    System.out.println("meeting acquired by user");
//                };
//                if (OntwikkelBedrijf.arrivedAtCompany.tryAcquire()){
//                    System.out.println("user arrived at company, getting ready for meeting..");
//                    Thread.sleep(2000);
//                    System.out.println("meeting prepared, starting meeting");
//                    OntwikkelBedrijf.readyForUserMeeting.release();

                }
            } catch (Exception ie){}
        }
    }
}
