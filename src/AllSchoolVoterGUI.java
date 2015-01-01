
import java.awt.Color;
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
    private String curesult;
    private String result;
    
    
    /**
     * Creates new form GradeVoterGUI
     */
    public AllSchoolVoterGUI(Setup toSetup, HelperMethods h, String c) {
        initComponents();
        setup=toSetup;
        helper=h;
        code=c;
        pcands.removeAllItems();
        String[] poptions = setup.getPresCandidates().toArray(new String[setup.getPresCandidates().size()]);
        for(String cand:poptions){
            pcands.addItem(cand);
        }
        vcands.removeAllItems();
        String[] voptions = setup.getVpCandidates().toArray(new String[setup.getVpCandidates().size()]);
        for(String cand:voptions){
            vcands.addItem(cand);
        }
        cands.removeAllItems();
        String[] coptions = setup.getCuCandidates().toArray(new String[setup.getCuCandidates().size()]);
        for(String cand:coptions){
            cands.addItem(cand);
        }
        ccands.removeAllItems();
        for(String cand:coptions){
            ccands.addItem(cand);
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
        ccands = new javax.swing.JComboBox();
        cands = new javax.swing.JComboBox();
        select = new javax.swing.JButton();
        SchoolLogo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Vote");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(102, 0, 0));
        setLocation(new java.awt.Point(100, 100));

        pcands.setEditable(true);
        pcands.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        vcands.setEditable(true);
        vcands.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        ccands.setEditable(true);
        ccands.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cands.setEditable(true);
        cands.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        select.setText("Enter");
        select.setForeground(Color.BLACK);
        select.setBackground(Color.WHITE);
        select.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectMouseClicked(evt);
            }
        });

        SchoolLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SchoolLogo.gif"))); // NOI18N
        SchoolLogo.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(200, Short.MAX_VALUE)
                .addComponent(SchoolLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(select)
                    .addComponent(pcands, 0, 300, Short.MAX_VALUE)
                    .addComponent(vcands, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cands, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ccands, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(200, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SchoolLogo)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(pcands, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vcands, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cands, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ccands, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(select)))
                .addContainerGap(139, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectMouseClicked
        presresult = (String) pcands.getSelectedItem();
        vpresult = (String) vcands.getSelectedItem();
        curesult = (String) ccands.getSelectedItem();
        result = (String) cands.getSelectedItem();
        voter();
    }//GEN-LAST:event_selectMouseClicked
    
    public void voter() {
        if ((!(presresult.equals("(Select Presedential Candidate or type in a write in)"))) && (!(vpresult.equals("(Select Vice Presidential Candidate or type in a write in)"))) && (!(curesult.equals("(Select Cultural Union Candidate or type in a write in)"))) && (!(result.equals("(Select Cultural Union Candidate or type in a write in)")))) {
            if (result.equals(curesult)) {
                JOptionPane.showMessageDialog(this, "You selected the same candidate for both Cultural Union inputs. You must select two different candidates");
            } else {
                FullBallot ballot = new FullBallot();
                ballot.setPres(presresult);
                ballot.setVp(vpresult);
                ballot.setCu1(curesult);
                ballot.setCu2(result);
                Thread thread;
                thread = new Thread(){
                    public void run(){
                        helper.vote(ballot, code);
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
                new GradeVoterGUI(toSetup,h,c).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel SchoolLogo;
    private javax.swing.JComboBox cands;
    private javax.swing.JComboBox ccands;
    private javax.swing.JComboBox pcands;
    private javax.swing.JButton select;
    private javax.swing.JComboBox vcands;
    // End of variables declaration//GEN-END:variables
}
