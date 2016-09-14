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
                OntwikkelBedrijf.probleem.tryAcquire();
            }
        }
    }
}
