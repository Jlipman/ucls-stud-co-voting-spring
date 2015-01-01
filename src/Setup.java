
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import java.nio.file.Path;
import java.util.Scanner;
import java.nio.file.Paths;

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
        int position = 0;
        Path path = Paths.get("candidates.txt");
        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    position++;
                } else if (position == 0) {
                    presc.add(line);
                } else if (position == 1) {
                    vpc.add(line);
                } else if (position == 2) {
                    cuc.add(line);
                }
            }
        } catch (Exception e) {
        }
    }

    public static void main(String U, String P) {

        ArrayList<String> foo = new ArrayList<String>();
        String link = U;
        String password = P;
        Drive d;

        d = new Drive(link, password, "Election");

        Random R = new Random();
        FileWriter writer = null;
        try {
            File file = new File("Codes.txt");
            file.createNewFile();
            writer = new FileWriter(file);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Generating and writing codes");
        String chars = "123456789qwertyuiopasdfghjkzxcvbnmQWERTYUPASFGHJKLZXCVBNM";
        int length = 0;

        int numberOfVoters = Integer.parseInt(JOptionPane.showInputDialog("Number of voters: "));
        DriveNewThreadSet newThread = new DriveNewThreadSet(d);
        int gradeSize = (numberOfVoters+3) / 3;
        for (int j = 1; j < numberOfVoters + 3; j++) {
            String next = "";
            if (j == gradeSize || j == gradeSize*2) {
                next = "grade";
            } else {

                for (int i = 0; i < 6; i++) {
                    char c = chars.charAt(R.nextInt(chars.length()));
                    next += c;
                }

            }

            d.set(1, j, next);

            for (int i = 2; i < 20; i++) {
                newThread.set(i, j, "0");
            }
            try {
                writer.write(next + "\n");
            } catch (Exception e) {
                System.out.println(e);
            }
            foo.add(next);
            length = j;
            System.out.println(j + "/" + (numberOfVoters+3));

        }
        for (int i = 1; i < 20; i++) {
            d.set(i, length + 1, "stop");
        }
        System.out.println("setting up document");
        for (int i = 1; i < 20; i++) {
            for (int k = 20; k < 38; k++) {
                if (k % 10 == 0) {
                    d.set(k, i, "0");
                } else {
                    newThread.set(k, i, "0");
                }
            }
            System.out.println(i + "/20");
        }
        try {
            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(foo);
        Email emailer = new Email(link, password);
        emailer.sendEmail("Enclosed are the codes for the next election. There are " + numberOfVoters + " of them. \nEnjoy. \n Jonathan Lipman", "Codes.txt");
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

    public void getDriveVals(String U, String P) {
        link = U;

        password = P;

        d = new Drive(link, password, "Election");
        if (!d.testConnection()) {
            JOptionPane.showMessageDialog(null, "Invalid Password. Please Restart Program", "Invalid Password. Please Restart Program", JOptionPane.ERROR_MESSAGE);
            System.exit(0);

        }

    }

    public String getLink() {
        return link;
    }

    public String getPassword() {
        return password;
    }

}
