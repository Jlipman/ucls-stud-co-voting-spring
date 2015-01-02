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
        boolean votedFor = false;
        if ((!uIndex.equals("not"))) {
            for(int i = 1; i<19; i++){
                if(!feed.get(index + i).getCell().getInputValue().equals("0")){
                    votedFor=true;
                    break;
                }
            }
            if (!votedFor) {
                ID = feed.get(index);
                return true;

            }
        }

        return false;
    }

    public void voteForGrade(GradeBallot who, String username) {

        String RCNotation = ID.getId().substring(ID.getId().lastIndexOf('/') + 1);
        //use grade variable here
        int y = Integer.parseInt(RCNotation.substring(1, RCNotation.indexOf("C")));
        int x = grade*4;
        pass.set(x+2, y, who.getPres());
        pass.set(x+3, y, who.getVp());
        pass.set(x+4, y, who.getCu1());
        pass.set(x+5, y, who.getCu2());
    }
    
    public void voteForAllSchool(AllSchoolBallot who, String username) {

        String RCNotation = ID.getId().substring(ID.getId().lastIndexOf('/') + 1);
        //use grade variable here
        int y = Integer.parseInt(RCNotation.substring(1, RCNotation.indexOf("C")));
        int x = 3*4;
        pass.set(x+2, y, who.getPres());
        pass.set(x+3, y, who.getVp());
        pass.set(x+4, y, who.getCupres());
        pass.set(x+5, y, who.getCuvp());
        pass.set(x+6, y, who.getSec());
        pass.set(x+7, y, who.getTres());
    }
    
    public int getGrade(){
        return grade;
    }
}
