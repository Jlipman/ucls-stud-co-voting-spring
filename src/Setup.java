import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.lang.Math;
import java.util.*;
import java.io.*;

/**
 * Write a description of class Setup here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Setup {

    private ArrayList<String> presc;
    private ArrayList<String> vpc;
    private ArrayList<String> cuc;
    private String link;
    private String password;
    private Drive d;

    public Setup() {
        presc = new ArrayList<String>();
        vpc = new ArrayList<String>();
        cuc = new ArrayList<String>();
        presc.add("(Select Presedential Candidate or type in a write in)");
        vpc.add("(Select Vice Presidential Candidate or type in a write in)");
        cuc.add("(Select Cultural Union Candidate or type in a write in)");
        link = "";
        password = "";
    }

    public void inputCands() {
        inputPres();
        inputVp();
        inputCu();
    }

    public void inputPres() {
        String input = JOptionPane.showInputDialog("Enter the names of the presidential candidates: ");
        if (input != null && !input.equals("")) {
            presc.add(input);
            inputPres();
        }
    }

    public void inputVp() {
        String input = JOptionPane.showInputDialog("Enter the names of the vice presidential candidates: ");
        if (input != null && !input.equals("")) {
            vpc.add(input);
            inputVp();
        }
    }

    public void inputCu() {
        String input = JOptionPane.showInputDialog("Enter the names of of the cultural union candidates: ");
        if (input != null && !input.equals("")) {
            cuc.add(input);
            inputCu();
        }
    }

    public static void main() {
        FileWriter writer = null;
        try {
            File file = new File("Codes.txt");
            file.createNewFile();
            writer = new FileWriter(file);
        } catch (Exception e) {
            System.out.println(e);
        }
        ArrayList<String> foo = new ArrayList<String>();
        String link = JOptionPane.showInputDialog("Enter Google Acount Username: ");
        while (link == null || link.equals("")) {
            link = JOptionPane.showInputDialog("Enter Google Acount Username: ");
        }
        String password = JOptionPane.showInputDialog("Enter Google Acount Password: ");
        while (password == null || link.equals("")) {
            password = JOptionPane.showInputDialog("Please Enter Acount Password: ");
        }
        Drive d = new Drive(link, password, "Election");
        Random R = new Random();
        String chars = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASFGHJKLZXCVBNM";
        int length = 0;

        int numberOfVOters = Integer.parseInt(JOptionPane.showInputDialog("Number of voters: "));
        for (int j = 1; j < numberOfVOters + 1; j++) {
            String next = "";
            for (int i = 0; i < 6; i++) {
                char c = chars.charAt(R.nextInt(chars.length()));
                next += c;
            }
            //we should probably use loops for some of this
            d.set(1, j, next);
            d.set(2, j, "0");
            d.set(3, j, "0");
            d.set(4, j, "0");
            d.set(5, j, "0");
            try {
                writer.write(next + "\n");
            } catch (Exception e) {
                System.out.println(e);
            }
            foo.add(next);
            length = j;
            System.out.println(j + "/" + (numberOfVOters));
        }
        d.set(1, length + 1, "stop");
        d.set(2, length + 1, "stop");
        d.set(3, length + 1, "stop");
        d.set(4, length + 1, "stop");
        d.set(5, length + 1, "stop");
        System.out.println("setting up document");
        for (int i = 1; i < 11; i++) {
            d.set(6, i, "0");
            d.set(7, i, "0");
            d.set(8, i, "0");
            d.set(9, i, "0");
            d.set(10, i, "0");
            d.set(11, i, "0");
            System.out.println(i + "/10");
        }
        try {
            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(foo);
    }

    public ArrayList<String> getPresCandidates() {
        return presc;
    }

    public ArrayList<String> getVpCandidates() {
        return vpc;
    }

    public ArrayList<String> getCuCandidates() {
        return cuc;
    }

    public void getDriveVals() {
        link = JOptionPane.showInputDialog("Enter Google Acount Username: ");
        while (link == null || link.equals("")) {
            link = JOptionPane.showInputDialog("Enter Google Acount Username: ");
        }
        password = JOptionPane.showInputDialog("Enter Google Acount Password: ");
        while (password == null || link.equals("")) {
            password = JOptionPane.showInputDialog("Please Enter Acount Password: ");
        }
        d = new Drive(link, password, "Election");
    }

    public String getPassword() {
        return password;
    }

    public String getlink() {
        return link;
    }
}
