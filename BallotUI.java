import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class BallotUI implements ActionListener
{
    private Setup sup;
    private JButton enter;
    private JButton quit;
    private JButton select;
    private JTextField codeField;
    private JComboBox<String> pcands;
    private JComboBox<String> vcands;
    private JComboBox<String> ccands;
    private JComboBox<String> cands;
    private JLabel instruc;
    private JFrame frame;
    private FAH f;
    private String code;
    private boolean valid;
    private String presresult;
    private String vpresult;
    private String curesult;
    private String result;
    public void run(){
        valid = false;
        //set up frame
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(new Dimension(1000,1000));
        frame.setTitle("UCLS Voting");
        frame.setVisible(true);
        Color maroon = new Color(128,0,0);
        frame.getContentPane().setBackground(maroon);
        //set up buttons
        quit = new JButton("End All Voting");
        quit.addActionListener(this);
        codeField = new JTextField(6);
        instruc= new JLabel("Enter 6-digit code (case sensitive): ");
        enter = new JButton("Enter");
        enter.addActionListener(this);

        //set up layout
        //JPanel North = new JPanel(new GridLayout(3,1));
        frame.add(instruc);
        frame.add(codeField);
        frame.add(enter);
        frame.add(quit);
    }

    public void Managment(){
        sup = new Setup();
        sup.getDriveVals();
        f = new FAH(sup.getlink(), sup.getPassword());
        sup.inputCands();


    }

    public void actionPerformed(ActionEvent e){
        Object src = e.getSource();
        if(src == enter){
            if(f.checkIfValid(codeField.getText())){
                valid = true;
                JOptionPane.showMessageDialog(null,"Success!");
                code = codeField.getText();
                enter.setVisible(false);
                codeField.setVisible(false);
                instruc.setVisible(false);
                //adds candidate selection dialog
                String[] poptions = sup.getPresCandidates().toArray(new String[sup.getPresCandidates().size()]);
                pcands = new JComboBox<String>(poptions);
                pcands.setEditable(true);
                frame.add(pcands);
                
                String[] voptions = sup.getVpCandidates().toArray(new String[sup.getVpCandidates().size()]);
                vcands = new JComboBox<String>(voptions);
                vcands.setEditable(true);
                frame.add(vcands);
                
                String[] coptions1 = sup.getCuCandidates().toArray(new String[sup.getCuCandidates().size()]);
                ccands = new JComboBox<String>(coptions1);
                ccands.setEditable(true);
                frame.add(ccands);
                
                String[] coptions2 = sup.getCuCandidates().toArray(new String[sup.getCuCandidates().size()]);
                cands = new JComboBox<String>(coptions2);
                cands.setEditable(true);
                frame.add(cands);
                
                
                select = new JButton("Vote");
                select.addActionListener(this);
                
                frame.add(select);
                
            }else{
                instruc.setText("You must have already voted or entered your ID in wrong");
            }
        }else if(src == quit){
            String input = JOptionPane.showInputDialog(null, "Enter Google Drive Password: ");
            if(input.equals(sup.getPassword())){
                System.exit(0);
            }else{
                JOptionPane.showMessageDialog(frame,"Wrong Password!");
            }
        }else if(src == select && valid){
            presresult = (String)pcands.getSelectedItem();
            vpresult = (String)vcands.getSelectedItem();
            curesult = (String)ccands.getSelectedItem();
            result = (String)cands.getSelectedItem();
            voter();
        }else if(src == select && !valid){
            JOptionPane.showMessageDialog(frame,"You must enter your code");
            
        }
    }

    public void voter(){
        if((!(presresult.equals("(Select Presedential Candidate or type in a write in)")))||(!(vpresult.equals("(Select Vice Presidential Candidate or type in a write in)")))||(!(curesult.equals("(Select Cultural Union Candidate or type in a write in)")))||(!(result.equals("(Select Cultural Union Candidate or type in a write in)")))){
            if(result.equals(curesult)){
                JOptionPane.showMessageDialog(frame, "You selected the same candidate for both Cultural Union inputs. You must select two different candidates");
            }else{
                fullBallot ballot = new fullBallot();
                ballot.pres=presresult;
                ballot.vp=vpresult;
                ballot.cu1=curesult;
                ballot.cu2=result;
                f.vote(ballot, code);
                JOptionPane.showMessageDialog(frame, "Vote Submitted");
                frame.setVisible(false);
                run();
            }
        }else{
            JOptionPane.showMessageDialog(frame, "You must either vote for somebody or make sure the space is blank");
        }
    }
    
    public void reset(){
        run();
    }
}
