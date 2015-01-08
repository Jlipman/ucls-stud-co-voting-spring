
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lipman
 */
public class AllSchoolVoterGUI extends javax.swing.JFrame {

private Setup setup;
    private HelperMethods helper;
    private String code;
    private String presresult;
    private String vpresult;
    private String cpresult;
    private String cvresult;
    private String secresult;
    private String tresresult;
    
    
    /**
     * Creates new form GradeVoterGUI
     */
    public AllSchoolVoterGUI(Setup toSetup, HelperMethods h, String c) {
        initComponents();
        setup=toSetup;
        helper=h;
        code=c;
        
        
        pcands.removeAllItems();
        String[] poptions = setup.getAllSPres();
        for(String cand:poptions){
            pcands.addItem(cand);
        }
        vcands.removeAllItems();
        String[] voptions = setup.getAllSVP();
        for(String cand:voptions){
            vcands.addItem(cand);
        }
        
        cpands.removeAllItems();
        String[] cvptions = setup.getAllSCPres();
        for(String cand:cvptions){
            cpands.addItem(cand);
        }
        cvands.removeAllItems();
        String[] cpoptions = setup.getAllSCVP();
        for(String cand:cpoptions){
            cvands.addItem(cand);
        }
        
        sec.removeAllItems();
        String[] secoptions = setup.getAllSSec();
        for(String cand:secoptions){
            sec.addItem(cand);
        }
        tres.removeAllItems();
        String[] tresoptions = setup.getAllSTres();
        for(String cand:tresoptions){
            tres.addItem(cand);
        }
        
        this.getContentPane().setBackground(Color.WHITE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pcands = new javax.swing.JComboBox();
        vcands = new javax.swing.JComboBox();
        cpands = new javax.swing.JComboBox();
        cvands = new javax.swing.JComboBox();
        select = new javax.swing.JButton();
        sec = new javax.swing.JComboBox();
        tres = new javax.swing.JComboBox();
        SchoolLogo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Vote");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(102, 0, 0));
        setLocation(new java.awt.Point(100, 100));

        pcands.setEditable(true);
        pcands.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        vcands.setEditable(true);
        vcands.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cpands.setEditable(true);
        cpands.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cvands.setEditable(true);
        cvands.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        select.setText("Enter");
        select.setForeground(Color.BLACK);
        select.setBackground(Color.WHITE);
        select.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectMouseClicked(evt);
            }
        });

        sec.setEditable(true);
        sec.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tres.setEditable(true);
        tres.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        SchoolLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SchoolLogo.gif"))); // NOI18N
        SchoolLogo.setText("jLabel1");

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel1.setText("Vote for Your All School Representatives");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SchoolLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(select)
                            .addComponent(pcands, 0, 300, Short.MAX_VALUE)
                            .addComponent(vcands, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sec, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tres, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cvands, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cpands, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(400, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SchoolLogo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(pcands, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vcands, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cpands, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cvands, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(select)))
                .addContainerGap(184, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectMouseClicked
        presresult = (String) pcands.getSelectedItem();
        vpresult = (String) vcands.getSelectedItem();
        cpresult = (String) cpands.getSelectedItem();
        cvresult = (String) cvands.getSelectedItem();
        secresult = (String) sec.getSelectedItem();
        tresresult = (String) tres.getSelectedItem();
        voter();
    }//GEN-LAST:event_selectMouseClicked
    
    public void voter() {
        if ((!(presresult.equals("(Select Presedential Candidate or type in a write in)"))) && (!(vpresult.equals("(Select Vice Presidential Candidate or type in a write in)"))) && (!(cpresult.equals("(Select Cultural Union Candidate or type in a write in)"))) && (!(cvresult.equals("(Select Cultural Union Candidate or type in a write in)")))) {
            if (cvresult.equals(cpresult)) {
                JOptionPane.showMessageDialog(this, "You selected the same candidate for both Cultural Union inputs. You must select two different candidates");
            } else {
                AllSchoolBallot ballot = new AllSchoolBallot();
                ballot.setPres(presresult);
                ballot.setVp(vpresult);
                ballot.setCupres(cpresult);
                ballot.setCuvp(cvresult);
                ballot.setSec(secresult);
                ballot.setTres(tresresult);
                Thread thread;
                thread = new Thread(){
                    public void run(){
                        helper.voteForAllSchool(ballot, code);
                    }
                };
                thread.start();
                
                this.dispose();
                CodeValidationGUI.main(setup, helper);
                //finish and restart
            }
        } else {
            JOptionPane.showMessageDialog(this, "You must either vote for somebody or make sure the space is blank");
        }
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(Setup toSetup, HelperMethods h, String c) {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GradeVoterGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GradeVoterGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GradeVoterGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GradeVoterGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AllSchoolVoterGUI(toSetup,h,c).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel SchoolLogo;
    private javax.swing.JComboBox cpands;
    private javax.swing.JComboBox cvands;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox pcands;
    private javax.swing.JComboBox sec;
    private javax.swing.JButton select;
    private javax.swing.JComboBox tres;
    private javax.swing.JComboBox vcands;
    // End of variables declaration//GEN-END:variables
}
