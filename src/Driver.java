
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lipman
 */
public class Driver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("Choose: Driver, Setup, or Results");
        if(input.equals("Driver")){
            StudentVoter.main();
        }else if (input.equals("Setup")){
            Setup.main();
        }else if (input.equals("Results")){
            Results.main();
        }else{
            JOptionPane.showMessageDialog(null, "Not a valid choice");
            main(args);
        }
    }
    
}
