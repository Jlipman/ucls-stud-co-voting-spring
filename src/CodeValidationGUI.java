
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
public class CodeValidationGUI extends javax.swing.JFrame {
    private Setup setup;
    private HelperMethods helper;
    private String code;
    /**
     * Creates new form CodeValidationGUI
     */
    public CodeValidationGUI(Setup toSetup, HelperMethods h) {
        initComponents();
        this.getContentPane().setBackground(Color.WHITE);
        setup=toSetup;
        helper=h;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Submit = new javax.swing.JButton();
        codeEntry = new javax.swing.JTextField();
        quit = new javax.swing.JButton();
        SchoolLogo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 0, 0));
        setLocation(new java.awt.Point(100, 100));

        Submit.setText("Enter");
        Submit.setForeground(Color.BLACK);
        Submit.setBackground(Color.WHITE);
        Submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitActionPerformed(evt);
            }
        });

        codeEntry.setText("Enter your 6 digit code");
        codeEntry.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                codeEntryMouseClicked(evt);
            }
        });
        codeEntry.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                codeEntryKeyTyped(evt);
            }
        });

        quit.setText("End Voting");
        quit.setForeground(Color.BLACK);
        quit.setBackground(Color.WHITE);
        quit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quitMouseClicked(evt);
            }
        });

        SchoolLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SchoolLogo.gif"))); // NOI18N
        SchoolLogo.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(300, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(codeEntry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Submit))
                    .addComponent(SchoolLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(300, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(quit, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(SchoolLogo)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codeEntry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Submit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
                .addComponent(quit, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitActionPerformed
        vote();
    }//GEN-LAST:event_SubmitActionPerformed

    private void codeEntryKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codeEntryKeyTyped
        if(evt.getKeyCode()==13){
            vote();
            System.out.println("enter");
        }
    }//GEN-LAST:event_codeEntryKeyTyped

    private void codeEntryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_codeEntryMouseClicked
        codeEntry.setText("");
    }//GEN-LAST:event_codeEntryMouseClicked

    private void quitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quitMouseClicked
        System.out.println(Thread.activeCount());
        String input = JOptionPane.showInputDialog(null, "Enter Google Drive Password: ");
        if (input.equals(setup.getPassword())) {
            if(Thread.activeCount()>2){
                JOptionPane.showInputDialog(null, "Still Writing Election Results. Please quit later.");
            }else{
                System.exit(0);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Wrong Password!");
        }
    }//GEN-LAST:event_quitMouseClicked
    
    private void vote(){
        if (helper.checkIfValid(codeEntry.getText())) {
               
                code = codeEntry.getText();
                this.dispose();
                //adds candidate selection dialog
                
                
                VoterGUI.main(setup, helper, code);

            } else {
                JOptionPane.showMessageDialog(this,"You must have already voted or entered your ID in wrong");
            }
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(Setup toSetup, HelperMethods h) {
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
            java.util.logging.Logger.getLogger(CodeValidationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CodeValidationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CodeValidationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CodeValidationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CodeValidationGUI(toSetup, h).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel SchoolLogo;
    private javax.swing.JButton Submit;
    private javax.swing.JTextField codeEntry;
    private javax.swing.JButton quit;
    // End of variables declaration//GEN-END:variables

}