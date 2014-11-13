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
import java.util.ArrayList;

public class FAH {

    private Drive pass;
    private CellEntry ID;
    private ArrayList<CellEntry> queue, votes;
    private Results results;

    public FAH(String username, String password) {
        pass = new Drive(username, password, "Election");
        results = new Results(username, password);
        queue = new ArrayList<CellEntry>();
        votes = new ArrayList<CellEntry>();
    }

    public boolean checkIfValid(String username) {
        int y = 0;
        int index = 0;
        String uIndex = "not";
        ArrayList<CellEntry> feed = pass.getList();
        while (!(feed.get(y).getCell().getInputValue().equals("stop"))) {
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

        int y = Integer.parseInt(RCNotation.substring(1, RCNotation.indexOf("C")));
        pass.set(2, y, who.pres);
        pass.set(3, y, who.vp);
        pass.set(4, y, who.cu1);
        pass.set(5, y, who.cu2);
    }
}
