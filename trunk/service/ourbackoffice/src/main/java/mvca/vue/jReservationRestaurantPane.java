/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jReservationRestaurantPane.java
 *
 * Created on 1 d�c. 2010, 10:25:14
 */

package mvca.vue;

import java.util.Date;
import mvca.entity.ReservationRestau;

/**
 *
 * @author Manou
 */
public class jReservationRestaurantPane extends javax.swing.JPanel implements EntityPane<ReservationRestau> {

    /** Creates new form jReservationRestaurantPane */
    public jReservationRestaurantPane() {
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
        editButton1 = new javax.swing.JButton();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        saveButton3 = new javax.swing.JButton();
        fieldFkclient = new javax.swing.JTextField();
        fieldId1 = new javax.swing.JTextField();
        idLabel1 = new javax.swing.JLabel();
        prenomLabel5 = new javax.swing.JLabel();
        buttonFwIDClient = new javax.swing.JButton();
        fieldFkIdRestaurant = new javax.swing.JTextField();
        buttonFwIDType = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();

        newButton1.setText("New");

        editButton1.setText("Edit");

        jLayeredPane4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        saveButton3.setText("Save");
        saveButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButton3ActionPerformed(evt);
            }
        });
        saveButton3.setBounds(510, 90, 70, 23);
        jLayeredPane4.add(saveButton3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        fieldFkclient.setEditable(false);
        fieldFkclient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldFkclientActionPerformed(evt);
            }
        });
        fieldFkclient.setBounds(290, 10, 100, 20);
        jLayeredPane4.add(fieldFkclient, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

        buttonFwIDClient.setText("Client:");
        buttonFwIDClient.setBounds(190, 10, 65, 23);
        jLayeredPane4.add(buttonFwIDClient, javax.swing.JLayeredPane.DEFAULT_LAYER);

        fieldFkIdRestaurant.setEditable(false);
        fieldFkIdRestaurant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldFkIdRestaurantActionPerformed(evt);
            }
        });
        fieldFkIdRestaurant.setBounds(310, 40, 100, 20);
        jLayeredPane4.add(fieldFkIdRestaurant, javax.swing.JLayeredPane.DEFAULT_LAYER);

        buttonFwIDType.setText("Restaurant:");
        buttonFwIDType.setBounds(190, 40, 100, 23);
        jLayeredPane4.add(buttonFwIDType, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDateChooser1.setBounds(60, 40, 89, 20);
        jLayeredPane4.add(jDateChooser1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(newButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editButton1)
                .addContainerGap(521, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
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
                .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void saveButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButton3ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_saveButton3ActionPerformed

    private void fieldFkclientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldFkclientActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_fieldFkclientActionPerformed

    private void fieldId1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldId1ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_fieldId1ActionPerformed

    private void fieldFkIdRestaurantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldFkIdRestaurantActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_fieldFkIdRestaurantActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonFwIDClient;
    private javax.swing.JButton buttonFwIDType;
    private javax.swing.JButton editButton1;
    private javax.swing.JTextField fieldFkIdRestaurant;
    private javax.swing.JTextField fieldFkclient;
    private javax.swing.JTextField fieldId1;
    private javax.swing.JLabel idLabel1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JButton newButton1;
    private javax.swing.JLabel prenomLabel5;
    private javax.swing.JButton saveButton3;
    // End of variables declaration//GEN-END:variables

   @Override
    public ReservationRestau getEntity() {
        ReservationRestau result = new ReservationRestau();



        if (fieldFkclient.getText() == "" || fieldFkIdRestaurant.getText() == "" ) {
            return null;
        } else {
            result.setFkIdClient(Integer.parseInt(fieldFkclient.getText()));
            result.setReservationRestauId(Integer.parseInt(fieldFkIdRestaurant.getText()));
           
        }


        if (this.fieldId1.getText() != "") {
            result.setReservationRestauId(Integer.parseInt(fieldId1.getText()));
        }

        result.setDate(jDateChooser1.getDate());

        return result;
    }

    @Override
    public void loadEntity(ReservationRestau entity) {
        this.fieldId1.setText(String.valueOf(entity.getReservationRestauId()));
        this.fieldFkclient.setText(String.valueOf(entity.getFkIdClient()));
        this.fieldFkIdRestaurant.setText(String.valueOf(entity.getReservationRestauId()));
        this.jDateChooser1.setDate(entity.getDate());
    }

    @Override
    public void clearField() {
        this.fieldId1.setText("");
        this.fieldFkclient.setText("");
        this.fieldFkIdRestaurant.setText("");
        this.jDateChooser1.setDate(new Date());
    }

  

}