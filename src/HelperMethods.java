import com.google.gdata.data.spreadsheet.*;
import java.util.ArrayList;

public class HelperMethods {

    private Drive pass;
    private int grade;
    private CellEntry ID;
    private ArrayList<CellEntry> queue, votes;
    private Results results;

    public HelperMethods(String username, String password) {
        pass = new Drive(username, password, "Election");
        results = new Results(username, password);
        queue = new ArrayList<CellEntry>();
        votes = new ArrayList<CellEntry>();
    }

    public boolean checkIfValid(String username) {
        int y = 0;
        int index = 0;
        grade=0;
        String uIndex = "not";
        ArrayList<CellEntry> feed = pass.getList();
        while (!(feed.get(y).getCell().getInputValue().equals("stop"))) {
            if (feed.get(y).getCell().getInputValue().equals("grade")) {
                grade++;
            }
            if (feed.get(y).getCell().getInputValue().equals(username)) {
                uIndex = feed.get(y).getId().substring(feed.get(y).getId().lastIndexOf('/') + 1);
                index = y;
                break;
            }
            y++;
        }
        ArrayList<String> votes1 = new ArrayList<String>();
        for (int i = 0; i < queue.size(); i++) {
            votes1.add(queue.get(i).getCell().getInputValue());
        }
        if ((!uIndex.equals("not"))) {
            String votedfor = feed.get(index + 1).getCell().getInputValue();
            if (votedfor.equals("0")) {
                ID = feed.get(index);
                return true;

            }
        }

        return false;
    }

    public void vote(FullBallot who, String username) {

        String RCNotation = ID.getId().substring(ID.getId().lastIndexOf('/') + 1);
        //use grade variable here
        int y = Integer.parseInt(RCNotation.substring(1, RCNotation.indexOf("C")));
        pass.set(2, y, who.getPres());
        pass.set(3, y, who.getVp());
        pass.set(4, y, who.getCu1());
        pass.set(5, y, who.getCu2());
    }
    public int getGrade(){
        return grade;
    }
}
