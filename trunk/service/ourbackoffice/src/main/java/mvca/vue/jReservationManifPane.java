/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jReservationHotelPane.java
 *
 * Created on 1 d�c. 2010, 10:21:03
 */

package mvca.vue;

import mvca.entity.ReservationHotel;
import mvca.entity.ReservationManif;

/**
 *
 * @author Manou
 */
public class jReservationManifPane extends javax.swing.JPanel implements EntityPane<ReservationManif>{

    /** Creates new form jReservationHotelPane */
    public jReservationManifPane() {
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

        jLayeredPane4 = new javax.swing.JLayeredPane();
        saveButton3 = new javax.swing.JButton();
        fieldFkLocalisation = new javax.swing.JTextField();
        fieldId1 = new javax.swing.JTextField();
        idLabel1 = new javax.swing.JLabel();
        prenomLabel5 = new javax.swing.JLabel();
        fieldDate = new javax.swing.JTextField();
        buttonFwIDClient = new javax.swing.JButton();
        fieldFkIdManif = new javax.swing.JTextField();
        buttonFwIDType = new javax.swing.JButton();
        fieldFkIdManif1 = new javax.swing.JTextField();
        buttonFwIDType1 = new javax.swing.JButton();
        editButton1 = new javax.swing.JButton();
        newButton1 = new javax.swing.JButton();

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

        fieldFkIdManif.setEditable(false);
        fieldFkIdManif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldFkIdManifActionPerformed(evt);
            }
        });
        fieldFkIdManif.setBounds(290, 40, 100, 20);
        jLayeredPane4.add(fieldFkIdManif, javax.swing.JLayeredPane.DEFAULT_LAYER);

        buttonFwIDType.setText("Manif:");
        buttonFwIDType.setBounds(190, 40, 70, 23);
        jLayeredPane4.add(buttonFwIDType, javax.swing.JLayeredPane.DEFAULT_LAYER);

        fieldFkIdManif1.setEditable(false);
        fieldFkIdManif1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldFkIdManif1ActionPerformed(evt);
            }
        });
        fieldFkIdManif1.setBounds(290, 40, 100, 20);
        jLayeredPane4.add(fieldFkIdManif1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        buttonFwIDType1.setText("Manif:");
        buttonFwIDType1.setBounds(190, 40, 70, 23);
        jLayeredPane4.add(buttonFwIDType1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        editButton1.setText("Edit");

        newButton1.setText("New");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(newButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editButton1)
                .addContainerGap(519, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newButton1)
                    .addComponent(editButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
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

    private void fieldFkIdManifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldFkIdManifActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_fieldFkIdManifActionPerformed

    private void fieldDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldDateActionPerformed

    private void fieldFkIdManif1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldFkIdManif1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldFkIdManif1ActionPerformed

    @Override
    public ReservationManif getEntity() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void loadEntity(ReservationManif entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clearField() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

 


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonFwIDClient;
    private javax.swing.JButton buttonFwIDType;
    private javax.swing.JButton buttonFwIDType1;
    private javax.swing.JButton editButton1;
    private javax.swing.JTextField fieldDate;
    private javax.swing.JTextField fieldFkIdManif;
    private javax.swing.JTextField fieldFkIdManif1;
    private javax.swing.JTextField fieldFkLocalisation;
    private javax.swing.JTextField fieldId1;
    private javax.swing.JLabel idLabel1;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JButton newButton1;
    private javax.swing.JLabel prenomLabel5;
    private javax.swing.JButton saveButton3;
    // End of variables declaration//GEN-END:variables

}
