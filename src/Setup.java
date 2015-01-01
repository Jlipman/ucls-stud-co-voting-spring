
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
    ArrayList[] allSchoolCandidates = new ArrayList[6];

    public ArrayList[] getAllSchoolCandidates() {
        return allSchoolCandidates;
    }

    ArrayList[][] candidates = new ArrayList[3][3];
    private String link;
    private String password;
    private Drive d;

    public Setup() {
        for (int i = 0; i < 6; i++) {
            allSchoolCandidates[i] = new ArrayList<String>();
            if (i == 0) {
                allSchoolCandidates[i].add("(Select All School Presedential Candidate or type in a write in)");
            } else if (i == 1) {
                allSchoolCandidates[i].add("(Select All School Vice Presedential Candidate or type in a write in)");
            } else if (i == 2) {
                allSchoolCandidates[i].add("(Select All School Cultural Union Presedential Candidate or type in a write in)");
            } else if (i == 3) {
                allSchoolCandidates[i].add("(Select All School Cultural Union Vice Presedential Candidate or type in a write in)");
            } else if (i == 4) {
                allSchoolCandidates[i].add("(Select All School Secratary Candidate or type in a write in)");
            } else if (i == 5) {
                allSchoolCandidates[i].add("(Select All School Treasury Candidate or type in a write in)");
            }
        }
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                candidates[x][y] = new ArrayList<String>();
                if (y == 0) {
                    candidates[x][y].add("(Select Presedential Candidate or type in a write in)");
                } else if (y == 1) {
                    candidates[x][y].add("(Select Vice Presidential Candidate or type in a write in)");
                } else {
                    candidates[x][y].add("(Select Cultural Union Candidate or type in a write in)");
                }
            }
        }

        presc = new ArrayList<String>();
        vpc = new ArrayList<String>();
        cuc = new ArrayList<String>();
        link = "";
        password = "";
    }

    public void inputCands() {
        int position = 0;
        int x = 0;
        int y = 0;
        Path path = Paths.get("candidates.txt");
        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    position++;
                    if (position > 6) {
                        y++;
                        if (y >2) {
                           y = 0;
                            x++; 
                        }
                    }
                } else
                if (position < 6) {
                    allSchoolCandidates[position].add(line);
                } else {
                    candidates[x][y].add(line);
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
        int gradeSize = (numberOfVoters + 3) / 3;
        for (int j = 1; j < numberOfVoters + 3; j++) {
            String next = "";
            if (j == gradeSize || j == gradeSize * 2) {
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
            System.out.println(j + "/" + (numberOfVoters + 3));

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

    public String[] getPresCandidates(int grade) {
        String[] temp = new String[candidates[grade][0].size()];
        int i = 0;
        for(String a:(ArrayList<String>)candidates[grade][0]){
            temp[i]=a;
            i++;
        }
        return temp;
    }

    public String[] getVpCandidates(int grade) {
        String[] temp = new String[candidates[grade][1].size()];
        int i = 0;
        for(String a:(ArrayList<String>)candidates[grade][1]){
            temp[i]=a;
            i++;
        }
        return temp;
    }

    public String[] getCuCandidates(int grade) {
        String[] temp = new String[candidates[grade][2].size()];
        int i = 0;
        for(String a:(ArrayList<String>)candidates[grade][2]){
            temp[i]=a;
            i++;
        }
        return temp;
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
    
    public String[] getAllSPres(){
        return getStringArrayAllSchoolCandidate(0);
    }

    public String[] getAllSVP(){
        return getStringArrayAllSchoolCandidate(1);
    }
    
    public String[] getAllSCPres(){
        return getStringArrayAllSchoolCandidate(2);
    }

    public String[] getAllSCVP(){
        return getStringArrayAllSchoolCandidate(3);
    }
    
    public String[] getAllSSec(){
        return getStringArrayAllSchoolCandidate(4);
    }

    public String[] getAllSTres(){
        return getStringArrayAllSchoolCandidate(5);
    }
    
    private String[] getStringArrayAllSchoolCandidate(int position){
        String[] temp = new String[allSchoolCandidates[position].size()];
        int i = 0;
        for(String a:(ArrayList<String>)allSchoolCandidates[position]){
            temp[i]=a;
            i++;
        }
        return temp;   
    }
}
