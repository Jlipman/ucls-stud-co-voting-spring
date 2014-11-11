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
import javax.swing.JOptionPane;
import java.util.*;
import java.io.IOException;
import java.net.*;
import java.util.*;

import java.io.*;
import com.google.gdata.client.spreadsheet.*;
import com.google.gdata.data.spreadsheet.*;
import com.google.gdata.util.*;

import java.io.IOException;
import java.net.*;

public class Results
{
    
    //TODO make this at least somewhat efficient 
    private Drive results;
    public Results(String username, String password) 
    {
        results = new Drive(username, password,"Results" );
    }

    public static void main(String[] args){
        String link = JOptionPane.showInputDialog("Enter Google Acount Username: ");
        while(link == null || link.equals("")){
            link = JOptionPane.showInputDialog("Enter Google Acount Username: ");
        }
        String password = JOptionPane.showInputDialog("Enter Google Acount Password: ");
        while(password == null || link.equals("")){
            password = JOptionPane.showInputDialog("Please Enter Acount Password: ");
        }
        Drive results = new Drive(link, password, "Election");
        ArrayList<String> pcandidates = new ArrayList<String>();
        ArrayList<Integer> ptally = new ArrayList<Integer>();
        ArrayList<String> vcandidates = new ArrayList<String>();
        ArrayList<Integer> vtally = new ArrayList<Integer>();
        ArrayList<String> ccandidates = new ArrayList<String>();
        ArrayList<Integer> ctally = new ArrayList<Integer>();

        String[] current = new String[4];

        ArrayList<CellEntry> drive = results.getList();

        for(int i=1; true; i++){
            for(int u=0; u<4; u++){
                current[u]=results.get(u+2,i);
            }
            if(current[1].equals("stop")){
                break;
            }else{
                if(!current[0].equals("0")){
                    boolean foo=false;
                    for(int u=0; u<pcandidates.size(); u++){
                        if(pcandidates.get(u).equals(current[0])){
                            ptally.set(u,ptally.get(u)+1);
                            foo=true;
                        }
                    }
                    if(!foo){
                        pcandidates.add(current[0]);
                        ptally.add(1);
                    }
                }
                if(!current[1].equals("0")){
                    boolean foo=false;
                    for(int u=0; u<vcandidates.size(); u++){
                        if(vcandidates.get(u).equals(current[1])){
                            vtally.set(u,vtally.get(u)+1);
                            foo=true;
                        }
                    }
                    if(!foo){
                        vcandidates.add(current[1]);
                        vtally.add(1);
                    }
                }
                if(!current[2].equals("0")){
                    boolean foo=false;
                    for(int u=0; u<ccandidates.size(); u++){
                        if(ccandidates.get(u).equals(current[2])){
                            ctally.set(u,ctally.get(u)+1);
                            foo=true;
                        }
                    }
                    if(!foo){
                        ccandidates.add(current[2]);
                        ctally.add(1);
                    }
                }
                if(!current[3].equals("0")){
                    boolean foo=false;
                    for(int u=0; u<ccandidates.size(); u++){
                        if(ccandidates.get(u).equals(current[3])){
                            ctally.set(u,ctally.get(u)+1);
                            foo=true;
                        }
                    }
                    if(!foo){
                        ccandidates.add(current[3]);
                        ctally.add(1);
                    }
                }    
            }
        }
        
        
        FileWriter writer=null;
        try{
            File file = new File("Winners.txt");
            file.createNewFile();
            writer = new FileWriter(file); 
        }catch(Exception e){System.out.println(e);}
        
        int biggest=0;
        int indexofbiggest=0;
        for(int i=0; i<ptally.size(); i++){
            if(ptally.get(i)>biggest){
                biggest=ptally.get(i);
                indexofbiggest=i;
            }
        }
        try{
            writer.write("President: "+pcandidates.get(indexofbiggest));
        }catch(Exception e){System.out.println(e);}
        System.out.println(pcandidates.get(indexofbiggest));
        for(int y=0; y<pcandidates.size(); y++){
            results.set(6,y+1,pcandidates.get(y));
        }
        for(int y=0; y<ptally.size(); y++){
            results.set(7,y+1,""+ptally.get(y));
        }
        
        biggest=0;
        indexofbiggest=0;
        for(int i=0; i<vtally.size(); i++){
            if(vtally.get(i)>biggest){
                biggest=vtally.get(i);
                indexofbiggest=i;
            }
        }
        try{
            writer.write("\nVice President: "+vcandidates.get(indexofbiggest));
        }catch(Exception e){System.out.println(e);}
        for(int y=0; y<vcandidates.size(); y++){
            results.set(8,y+1,vcandidates.get(y));
        }
        for(int y=0; y<vtally.size(); y++){
            results.set(9,y+1,""+vtally.get(y));
        }
        biggest=0;
        indexofbiggest=0;
        for(int i=0; i<ctally.size(); i++){
            if(ctally.get(i)>biggest){
                biggest=ctally.get(i);
                indexofbiggest=i;
            }
        }
        try{
            writer.write("\nCultural Union 1: "+ccandidates.get(indexofbiggest));
        }catch(Exception e){System.out.println(e);}
        for(int y=0; y<ccandidates.size(); y++){
            results.set(10,y+1,ccandidates.get(y));
        }
        for(int y=0; y<ctally.size(); y++){
            results.set(11,y+1,""+ctally.get(y));
        }
        ccandidates.remove(indexofbiggest);
        ctally.remove(indexofbiggest);
        
        biggest=0;
        indexofbiggest=0;
        for(int i=0; i<ctally.size(); i++){
            if(ctally.get(i)>biggest){
                biggest=ctally.get(i);
                indexofbiggest=i;
            }
        }
        
        try{
            writer.write("\nCultural Union 2: "+ccandidates.get(indexofbiggest));
        }catch(Exception e){System.out.println(e);}
        
        try{
            writer.flush();
            writer.close();
        }catch(Exception e){System.out.println(e);}
        
        System.out.println("go online to check winners");
    }

    private int searchForEmpty(int col){
        int y=0;
        while(!(results.get(col, y).equals("0"))){
            y++;
        }
        return y;
    }

}
