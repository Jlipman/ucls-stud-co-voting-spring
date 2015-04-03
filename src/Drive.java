import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.CellEntry;
import com.google.gdata.data.spreadsheet.CellFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.google.gdata.client.spreadsheet.*;
import com.google.gdata.data.spreadsheet.*;
import com.google.gdata.util.*;

public class Drive {

    SpreadsheetService service;
    SpreadsheetEntry spreadsheet;
    WorksheetEntry worksheet;
    SpreadsheetFeed feed;
    URL cellFeedUrl;
    CellFeed cellFeed;
    

    //password: notasecret

    public Drive(String username, String password, String nameOfSpreadsheet) {
        try {
            service
                    = new SpreadsheetService("MySpreadsheetIntegration-v1");
            service.setUserCredentials(username, password);
            URL SPREADSHEET_FEED_URL = new URL(
                    "https://spreadsheets.google.com/feeds/spreadsheets/private/full");

            // Make a request to the API and get all spreadsheets.
            feed = service.getFeed(SPREADSHEET_FEED_URL,
                    SpreadsheetFeed.class);
            List<SpreadsheetEntry> spreadsheets = feed.getEntries();

            spreadsheet = spreadsheets.get(0);
            System.out.println(spreadsheet.getTitle().getPlainText());

            for (SpreadsheetEntry a : spreadsheets) {
                if (a.getTitle().getPlainText().equals(nameOfSpreadsheet)) {
                    spreadsheet = a;
                }
            }

            List<WorksheetEntry> worksheets = spreadsheet.getWorksheets();
            worksheet = worksheets.get(0);
            cellFeedUrl = worksheet.getCellFeedUrl();
            cellFeed = service.getFeed(cellFeedUrl, CellFeed.class);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void set(int x, int y, String value) {
        try {
            for (CellEntry cell : cellFeed.getEntries()) {
                if (cell.getId().substring(cell.getId().lastIndexOf('/') + 1).equals("R" + y + "C" + x)) {
                    if(!cell.getCell().getInputValue().equals(value)){
                        cell.changeInputValueLocal(value);
                        cell.update();
                    }
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            set(x,y,value);
        }
    }
    
    public String get(int x, int y) {
        String values = "";
        try {
            for (CellEntry cell : cellFeed.getEntries()) {
                if (cell.getId().substring(cell.getId().lastIndexOf('/') + 1).equals("R" + y + "C" + x)) {
                    values = cell.getCell().getInputValue();
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
            return get(x, y);
        }
        return values;
    }

    public ArrayList<CellEntry> getList() {
        resetLink();
        ArrayList<CellEntry> foo = new ArrayList<CellEntry>();
        try {
            for (CellEntry cell : cellFeed.getEntries()) {
                foo.add(cell);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return foo;
    }

    public boolean testConnection() {
       try {
           cellFeedUrl = worksheet.getCellFeedUrl();
           CellFeed cellFeed = service.getFeed(cellFeedUrl, CellFeed.class);

            return true;

        } catch (Exception e) {
            return false;
        }
    }
    
    public void resetLink(){
        try {
            cellFeedUrl = worksheet.getCellFeedUrl();
            cellFeed = service.getFeed(cellFeedUrl, CellFeed.class);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void cleanWorksheet(){
        System.out.println("Clearing Spreadsheet");
        try {
            cellFeedUrl = worksheet.getCellFeedUrl();
            cellFeed = service.getFeed(cellFeedUrl, CellFeed.class);
            for (CellEntry cell : cellFeed.getEntries()) {
                
                if (!cell.getId().substring(cell.getId().lastIndexOf('/') + 1).contains("C" + 0)) {
                    System.out.println(cell.getId().substring(cell.getId().lastIndexOf('/') + 1));
                    if(!cell.getCell().getInputValue().equals("0")){
                        cell.changeInputValueLocal("0");
                        cell.update();
                    }
                }
            }
            
    } catch (Exception e) {
            System.out.println(e);
        }
    }
}   
