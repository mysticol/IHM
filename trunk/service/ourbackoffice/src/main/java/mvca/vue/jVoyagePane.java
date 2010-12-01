/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jReservationPane.java
 *
 * Created on 1 d�c. 2010, 10:37:42
 */

package mvca.vue;

import mvca.entity.Voyage;

/**
 *
 * @author Manou
 */
public class jVoyagePane extends javax.swing.JPanel implements EntityPane<Voyage> {

    /** Creates new form jReservationPane */
    public jVoyagePane() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        newButton1 = new javax.swing.JButton();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        saveButton3 = new javax.swing.JButton();
        fieldFkLocalisation = new javax.swing.JTextField();
        fieldId1 = new javax.swing.JTextField();
        idLabel1 = new javax.swing.JLabel();
        prenomLabel5 = new javax.swing.JLabel();
        fieldDate = new javax.swing.JTextField();
        buttonFwIDClient = new javax.swing.JButton();
        fieldFkIdDepart = new javax.swing.JTextField();
        buttonFwIDDepart = new javax.swing.JButton();
        fieldFkIdArrive = new javax.swing.JTextField();
        buttonFwIDArrive = new javax.swing.JButton();
        editButton1 = new javax.swing.JButton();

        newButton1.setText("New");

        jLayeredPane4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        saveButton3.setText("Save");
        saveButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButton3ActionPerformed(evt);
            }
        });
        saveButton3.setBounds(510, 150, 70, 23);
        jLayeredPane4.add(saveButton3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        fieldFkLocalisation.setEditable(false);
        fieldFkLocalisation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldFkLocalisationActionPerformed(evt);
            }
        });
        fieldFkLocalisation.setBounds(290, 10, 100, 20);
        jLayeredPane4.add(fieldFkLocalisation, javax.swing.JLayeredPane.DEFAULT_LAYER);

        fieldId1.setEditable(false);
        fieldId1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldId1ActionPerformed(evt);
            }
        });
        fieldId1.setBounds(60, 10, 50, 20);
        jLayeredPane4.add(fieldId1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        idLabel1.setText("Id: ");
        idLabel1.setBounds(10, 10, 17, 14);
        jLayeredPane4.add(idLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        prenomLabel5.setText("Date:");
        prenomLabel5.setBounds(10, 40, 60, 14);
        jLayeredPane4.add(prenomLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        fieldDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldDateActionPerformed(evt);
            }
        });
        fieldDate.setBounds(60, 40, 100, 20);
        jLayeredPane4.add(fieldDate, javax.swing.JLayeredPane.DEFAULT_LAYER);

        buttonFwIDClient.setText("Client:");
        buttonFwIDClient.setBounds(190, 10, 65, 23);
        jLayeredPane4.add(buttonFwIDClient, javax.swing.JLayeredPane.DEFAULT_LAYER);

        fieldFkIdDepart.setEditable(false);
        fieldFkIdDepart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldFkIdDepartActionPerformed(evt);
            }
        });
        fieldFkIdDepart.setBounds(290, 40, 100, 20);
        jLayeredPane4.add(fieldFkIdDepart, javax.swing.JLayeredPane.DEFAULT_LAYER);

        buttonFwIDDepart.setText("Depart:");
        buttonFwIDDepart.setBounds(190, 40, 80, 23);
        jLayeredPane4.add(buttonFwIDDepart, javax.swing.JLayeredPane.DEFAULT_LAYER);

        fieldFkIdArrive.setEditable(false);
        fieldFkIdArrive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldFkIdArriveActionPerformed(evt);
            }
        });
        fieldFkIdArrive.setBounds(290, 70, 100, 20);
        jLayeredPane4.add(fieldFkIdArrive, javax.swing.JLayeredPane.DEFAULT_LAYER);

        buttonFwIDArrive.setText("Arrive:");
        buttonFwIDArrive.setBounds(190, 70, 70, 23);
        jLayeredPane4.add(buttonFwIDArrive, javax.swing.JLayeredPane.DEFAULT_LAYER);

        editButton1.setText("Edit");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 627, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(newButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editButton1)
                .addContainerGap(492, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 252, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newButton1)
                    .addComponent(editButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void saveButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButton3ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_saveButton3ActionPerformed

    private void fieldFkLocalisationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldFkLocalisationActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_fieldFkLocalisationActionPerformed

    private void fieldId1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldId1ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_fieldId1ActionPerformed

    private void fieldDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldDateActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_fieldDateActionPerformed

    private void fieldFkIdDepartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldFkIdDepartActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_fieldFkIdDepartActionPerformed

    private void fieldFkIdArriveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldFkIdArriveActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_fieldFkIdArriveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonFwIDArrive;
    private javax.swing.JButton buttonFwIDClient;
    private javax.swing.JButton buttonFwIDDepart;
    private javax.swing.JButton editButton1;
    private javax.swing.JTextField fieldDate;
    private javax.swing.JTextField fieldFkIdArrive;
    private javax.swing.JTextField fieldFkIdDepart;
    private javax.swing.JTextField fieldFkLocalisation;
    private javax.swing.JTextField fieldId1;
    private javax.swing.JLabel idLabel1;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JButton newButton1;
    private javax.swing.JLabel prenomLabel5;
    private javax.swing.JButton saveButton3;
    // End of variables declaration//GEN-END:variables

    @Override
    public Voyage getEntity() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void loadEntity(Voyage entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clearField() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   

}
