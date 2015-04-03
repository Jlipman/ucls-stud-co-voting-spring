
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lipman
 */
public class DriveNewThreadSet {

    private Drive d;
    Random R;
    public DriveNewThreadSet(Drive dPrime) {
        d=dPrime;
        
        
    }

    public void set(final int x, final int y, final String value) {
        System.out.println("creating new thread");
        Thread thread;
        thread = new Thread() {
            public void run() {
                try{
                d.set(x, y, value);
                System.out.println("thread finished");
                }catch(Exception e){System.out.println(e);}
            }
        };
        thread.start();
    }
}
