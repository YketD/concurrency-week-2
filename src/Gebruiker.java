/**
 * Created by yketd on 14-9-2016.
 */
public class Gebruiker extends Thread {

    public void run(){
        while(true) {
            gebruiken();
            meldProbleem();
        }
    }

    private void gebruiken() {
        try {
            System.out.println("gebruiker gebruikt app..");
            Thread.sleep(10000);
        } catch (InterruptedException ie) {
        }
    }

    public void meldProbleem(){
        try {
            OntwikkelBedrijf.probleem.release();
        }   catch (InterruptedException ie){}
    }
}
