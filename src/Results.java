
import com.google.gdata.data.spreadsheet.*;
import java.util.*;

import java.io.*;
import javax.swing.JOptionPane;

public class Results {

    private Drive results;
    static final String[] positions = {"President", "Vice President", "Cultural Union"};

    public Results(String username, String password) {
        results = new Drive(username, password, "Results");
    }

    public static void main(String link, String password) {
        Setup setup = new Setup();

        setup.getDriveVals(link, password);
        Drive results = new Drive(link, password, "Election");
        ArrayList[][] candidates = new ArrayList[18][2];
        DriveNewThreadSet newThread = new DriveNewThreadSet(results);

        String[] current = new String[18];

        for (int x = 0; x < 18; x++) {
            for (int y = 0; y < 2; y++) {
                if (y == 0) {
                    candidates[x][y] = new ArrayList<String>();
                } else {
                    candidates[x][y] = new ArrayList<Integer>();
                }

            }
        }

        ArrayList<CellEntry> drive = results.getList();

        for (int i = 1; true; i++) {
            System.out.println(i);
            for (int u = 0; u < 18; u++) {
                current[u] = get(u + 2, i, drive);
            }
            if (current[1].equals("stop")) {
                break;
            } else {
                for (int j = 0; j < 18; j++) {
                    if (!current[j].equals("0")) {
                        boolean voteAdded = false;
                        for (int u = 0; u < candidates[j][0].size(); u++) {
                            if (candidates[j][0].get(u).equals(current[j])) {
                                candidates[j][1].set(u, (Integer) candidates[j][1].get(u) + 1);
                                voteAdded = true;
                            }
                        }
                        if (!voteAdded) {
                            candidates[j][0].add((String) (current[j]));
                            candidates[j][1].add(1);
                        }
                    }
                }
            }
        }
        for(int i = 2; i<11; i+=4){
            //agregate the two cu columns
            for (int x = 0; x < candidates[i+1][0].size(); x++) {
                String cand = (String) candidates[i+1][0].get(x);
                for (int y = 0; y < candidates[i][0].size(); y++) {
                    if (cand.equals(candidates[i][0].get(y))) {
                        candidates[i][1].set(y, (Integer) candidates[i+1][1].get(x) + (Integer) (candidates[i][1].get(y)));
                        candidates[i+1][0].remove(x);
                        candidates[i+1][1].remove(x);
                        x--;
                        break;
                    }
                }
            }
            for (int f = 0; f < candidates[i+1][0].size(); f++) {
                candidates[i][0].add(candidates[i+1][0].get(f));
                candidates[i][1].add(candidates[i+1][1].get(f));
            }
        }

        System.out.println("finished fethching data");
        System.out.println("calculating winners");
        FileWriter writer = null;
        try {
            File file = new File("Winners.txt");
            file.createNewFile();
            writer = new FileWriter(file);
            writer.write("Election Results\n");
        } catch (Exception e) {
            System.out.println(e);
        }
        int biggest = 0;
        int indexofbiggest = 0;
        int spreadsheetx =0;
        for (int j = 0; j < 18; j++) {
            
            spreadsheetx++;
            if (j == 3||j==7||j==11) {
                candidates[j-1][0].remove(indexofbiggest);
                candidates[j-1][1].remove(indexofbiggest);

                biggest = 0;
                indexofbiggest = 0;
                for (int i = 0; i < candidates[j-1][1].size(); i++) {
                    if ((Integer) candidates[j-1][1].get(i) > biggest) {
                        biggest = (Integer) candidates[j-1][1].get(i);
                        indexofbiggest = i;
                    }
                }
                checkForTie(j-1, candidates, indexofbiggest, biggest, writer);
                try {
                    writer.write(/*positions[j-1] + " " + */candidates[j-1][0].get(indexofbiggest) + "\n");
                } catch (Exception e) {
                    System.out.println(e);
                }
                
                continue;
            }
            
            biggest = 0;
            indexofbiggest = 0;
            for (int i = 0; i < candidates[j][1].size(); i++) {
                if ((Integer) candidates[j][1].get(i) > biggest) {
                    biggest = (Integer) candidates[j][1].get(i);
                    indexofbiggest = i;
                }
            }

            //deal with the possibility of a tie for pres and vice pres
            if (j < 2||(j>3&&j<6)||(j>7&&j<10)||(j>11)) {
                checkForTie(j, candidates, indexofbiggest, biggest, writer);
            }

            try {
                writer.write(/*positions[j] + " " + */candidates[j][0].get(indexofbiggest) + "\n");
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println(candidates[j][0].get(indexofbiggest));
            for (int y = 0; y < candidates[j][0].size(); y++) {
                newThread.set((spreadsheetx*2) + 20, y + 1, candidates[j][0].get(y).toString());
                results.set((spreadsheetx*2) + 21, y + 1, "" + candidates[j][1].get(y).toString());
            }

        }

        try {
            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        Email emailer = new Email(link, password);
        emailer.sendEmail("Enclosed are the winners of the Election. \nThank you for using my software. If you find any bugs please contact me. \n Jonathan Lipman", "Winners.txt");
        System.out.println("go online to check winners");
    }

    private int searchForEmpty(int col) {
        int y = 0;
        while (!(results.get(col, y).equals("0"))) {
            y++;
        }
        return y;
    }

    private static String get(int x, int y, ArrayList<CellEntry> list) {
        String values = "";
        for (CellEntry cell : list) {
            if (cell.getId().substring(cell.getId().lastIndexOf('/') + 1).equals("R" + y + "C" + x)) {
                values = cell.getCell().getInputValue();
                break;
            }
        }
        return values;
    }

    private static void checkForTie(int j, ArrayList[][] candidates, int indexofbiggest, int biggest, FileWriter writer) {
        for (int i = 0; i < candidates[j][1].size(); i++) {
            if (i != indexofbiggest && (Integer) candidates[j][1].get(i) == biggest) {
                try {
                    JOptionPane.showMessageDialog(null, "Tie for " + positions[j]);
                    System.out.println("Tie for " + positions[j]);
                    writer.write("Tie for " + positions[j] + ". Ignore these results and Contact Election Head and look on google spreasheet\n");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
}
