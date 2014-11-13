import com.google.gdata.client.authn.oauth.*;
import com.google.gdata.client.spreadsheet.*;
import com.google.gdata.data.*;
import com.google.gdata.data.batch.*;
import com.google.gdata.data.spreadsheet.*;
import com.google.gdata.util.*;
import com.google.gdata.client.authn.oauth.GoogleOAuthHelper;
import com.google.gdata.client.authn.oauth.GoogleOAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthHmacSha1Signer;
import com.google.gdata.client.authn.oauth.OAuthRsaSha1Signer;
import com.google.gdata.client.authn.oauth.OAuthSigner;
import com.google.gdata.client.spreadsheet.*;
import com.google.gdata.data.Link;
import com.google.gdata.data.batch.BatchOperationType;
import com.google.gdata.data.batch.BatchStatus;
import com.google.gdata.data.batch.BatchUtils;
import com.google.gdata.data.spreadsheet.*;
import com.google.gdata.util.*;
import java.io.IOException;
import java.net.*;
import java.util.*;
import java.io.IOException;
import java.net.*;
import java.util.*;

import com.google.gdata.client.spreadsheet.*;
import com.google.gdata.data.spreadsheet.*;
import com.google.gdata.util.*;

import java.io.IOException;
import java.net.*;

public class Drive {

    SpreadsheetService service;
    SpreadsheetEntry spreadsheet;
    WorksheetEntry worksheet;
    SpreadsheetFeed feed;

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
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void set(int x, int y, String value) {
        try {
            URL cellFeedUrl = worksheet.getCellFeedUrl();
            CellFeed cellFeed = service.getFeed(cellFeedUrl, CellFeed.class);
            for (CellEntry cell : cellFeed.getEntries()) {
                if (cell.getId().substring(cell.getId().lastIndexOf('/') + 1).equals("R" + y + "C" + x)) {
                    cell.changeInputValueLocal(value);
                    cell.update();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String get(int x, int y) {
        String values = "";
        try {

            URL cellFeedUrl = worksheet.getCellFeedUrl();
            CellFeed cellFeed = service.getFeed(cellFeedUrl, CellFeed.class);
            for (CellEntry cell : cellFeed.getEntries()) {
                if (cell.getId().substring(cell.getId().lastIndexOf('/') + 1).equals("R" + y + "C" + x)) {
                    values = cell.getCell().getInputValue();
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return values;
    }

    public ArrayList<CellEntry> getList() {
        ArrayList<CellEntry> foo = new ArrayList<CellEntry>();
        try {
            URL cellFeedUrl = worksheet.getCellFeedUrl();
            CellFeed cellFeed = service.getFeed(cellFeedUrl, CellFeed.class);

            for (CellEntry cell : cellFeed.getEntries()) {
                foo.add(cell);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return foo;
    }
}
