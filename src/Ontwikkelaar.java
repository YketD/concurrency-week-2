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

    }
}
