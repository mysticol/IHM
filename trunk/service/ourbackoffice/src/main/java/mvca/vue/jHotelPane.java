/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jHotelPane.java
 *
 * Created on 1 d�c. 2010, 09:32:50
 */

package mvca.vue;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import mvca.entity.Hotel;

/**
 *
 * @author Manou
 */
public class jHotelPane extends javax.swing.JPanel implements EntityPane<Hotel>{

    /** Creates new form jHotelPane */
    public jHotelPane() {
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

        saveButton1 = new javax.swing.JButton();
        prenomLabel1 = new javax.swing.JLabel();
        fieldFkLocalisation = new javax.swing.JTextField();
        fieldAdresse = new javax.swing.JTextField();
        fieldId1 = new javax.swing.JTextField();
        idLabel1 = new javax.swing.JLabel();
        nbcouvertLabel = new javax.swing.JLabel();
        fieldNbCouvert = new javax.swing.JTextField();
        prenomLabel4 = new javax.swing.JLabel();
        fieldHotel = new javax.swing.JTextField();
        prenomLabel2 = new javax.swing.JLabel();
        fieldPrix = new javax.swing.JTextField();
        nbcouvertLabel1 = new javax.swing.JLabel();
        fieldPrixMoyen = new javax.swing.JTextField();
        prenomLabel5 = new javax.swing.JLabel();
        fieldNote = new javax.swing.JTextField();
        buttonFwIDLocalisation = new javax.swing.JButton();
        editButton1 = new javax.swing.JButton();
        newButton1 = new javax.swing.JButton();

        saveButton1.setText("Save");
        saveButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButton1ActionPerformed(evt);
            }
        });

        prenomLabel1.setText("Adresse:");

        fieldFkLocalisation.setEditable(false);
        fieldFkLocalisation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldFkLocalisationActionPerformed(evt);
            }
        });

        fieldId1.setEditable(false);
        fieldId1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldId1ActionPerformed(evt);
            }
        });

        idLabel1.setText("Id: ");

        nbcouvertLabel.setText("Nb Chambre");

        prenomLabel4.setText("Nom:");

        prenomLabel2.setText("Prix:");

        nbcouvertLabel1.setText("Prix moyen:");

        prenomLabel5.setText("Note:");

        buttonFwIDLocalisation.setText("Localisation:");

        editButton1.setText("Edit");

        newButton1.setText("New");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(idLabel1)
                        .addGap(33, 33, 33)
                        .addComponent(fieldId1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(prenomLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fieldHotel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(prenomLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fieldNote, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(prenomLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fieldAdresse, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(prenomLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fieldPrix, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nbcouvertLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fieldNbCouvert, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(nbcouvertLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fieldPrixMoyen, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(buttonFwIDLocalisation)
                        .addGap(7, 7, 7)
                        .addComponent(fieldFkLocalisation, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(newButton1)
                        .addGap(5, 5, 5)
                        .addComponent(editButton1)
                        .addGap(7, 7, 7)
                        .addComponent(saveButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idLabel1)
                    .addComponent(fieldId1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(prenomLabel4)
                    .addComponent(fieldHotel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prenomLabel5)
                    .addComponent(fieldNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(prenomLabel1)
                    .addComponent(fieldAdresse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prenomLabel2)
                    .addComponent(fieldPrix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nbcouvertLabel)
                    .addComponent(fieldNbCouvert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nbcouvertLabel1)
                    .addComponent(fieldPrixMoyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonFwIDLocalisation)
                    .addComponent(fieldFkLocalisation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(newButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(editButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(saveButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void saveButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButton1ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_saveButton1ActionPerformed

    private void fieldFkLocalisationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldFkLocalisationActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_fieldFkLocalisationActionPerformed

    private void fieldId1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldId1ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_fieldId1ActionPerformed





    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonFwIDLocalisation;
    private javax.swing.JButton editButton1;
    private javax.swing.JTextField fieldAdresse;
    private javax.swing.JTextField fieldFkLocalisation;
    private javax.swing.JTextField fieldHotel;
    private javax.swing.JTextField fieldId1;
    private javax.swing.JTextField fieldNbCouvert;
    private javax.swing.JTextField fieldNote;
    private javax.swing.JTextField fieldPrix;
    private javax.swing.JTextField fieldPrixMoyen;
    private javax.swing.JLabel idLabel1;
    private javax.swing.JLabel nbcouvertLabel;
    private javax.swing.JLabel nbcouvertLabel1;
    private javax.swing.JButton newButton1;
    private javax.swing.JLabel prenomLabel1;
    private javax.swing.JLabel prenomLabel2;
    private javax.swing.JLabel prenomLabel4;
    private javax.swing.JLabel prenomLabel5;
    private javax.swing.JButton saveButton1;
    // End of variables declaration//GEN-END:variables


      @Override
    public Hotel getEntity() {
        Hotel result= new Hotel();

        if (fieldFkLocalisation.getText()==""){
            return null;
        }else {
            result.setFkLocalisationId(Integer.parseInt(fieldFkLocalisation.getText()));
        }

        if( this.fieldId1.getText()==""){
            result.setHotelId(Integer.parseInt(fieldId1.getText()));
        }

        result.setAdresse(fieldAdresse.getText());
        result.setNomHotel(fieldHotel.getText());



        if( this.fieldNbCouvert.getText()!=""){
            try{
            result.setNbChambre(Integer.parseInt(fieldNbCouvert.getText()));
            }catch(Exception e){
                 JOptionPane.showMessageDialog(getParent(), "Entier requis pour nbcouvert", "Dialog",
        JOptionPane.ERROR_MESSAGE);
                 return null;
            }
        }else{
        result.setNbChambre(0);
        }

         if( this.fieldNote.getText()!=""){
            try{
            result.setRankHotel(Integer.parseInt(fieldNote.getText()));
            }catch(Exception e){
                 JOptionPane.showMessageDialog(getParent(), "Entier requis pour rank", "Dialog",
        JOptionPane.ERROR_MESSAGE);
                 return null;
            }
        }else{
        result.setRankHotel(0);
        }

         if( this.fieldPrix.getText()!=""){
            try{
            result.setPrix(Double.parseDouble(fieldPrix.getText()));
            }catch(Exception e){
                 JOptionPane.showMessageDialog(getParent(), "Entier requis pour prix", "Dialog",
        JOptionPane.ERROR_MESSAGE);
                 return null;
            }
        }else{
        result.setPrix(0d);
        }


       if( this.fieldPrixMoyen.getText()!=""){
            try{
            result.setPrixMoyen(Double.parseDouble(fieldPrixMoyen.getText()));
            }catch(Exception e){
                 JOptionPane.showMessageDialog(getParent(), "Entier requis pour prix", "Dialog",
        JOptionPane.ERROR_MESSAGE);
                 return null;
            }
        }else{
        result.setPrixMoyen(0d);
        }








        return result;
    }

    @Override
    public void loadEntity(Hotel entity) {
        this.fieldAdresse.setText(entity.getAdresse());
        this.fieldFkLocalisation.setText(String.valueOf(entity.getFkLocalisationId()));
        this.fieldHotel.setText(entity.getNomHotel());
        this.fieldNote.setText(String.valueOf(entity.getRankHotel()));
        this.fieldId1.setText(String.valueOf(entity.getHotelId()));
        this.fieldNbCouvert.setText(String.valueOf(entity.getNbChambre()));
        this.fieldPrix.setText(String.valueOf(entity.getPrix()));
        this.fieldPrixMoyen.setText(String.valueOf(entity.getPrixMoyen()));
    }

    @Override
    public void clearField() {
        this.fieldAdresse.setText("");
        this.fieldFkLocalisation.setText("");
        this.fieldHotel.setText("");
        this.fieldNote.setText("");
        this.fieldId1.setText("");
        this.fieldNbCouvert.setText("");
        this.fieldPrix.setText("");
        this.fieldPrixMoyen.setText("");

    }

    public JButton getButtonFwIDLocalisation() {
        return buttonFwIDLocalisation;
    }

    public JButton getEditButton() {
        return editButton1;
    }

    public JButton getNewButton() {
        return newButton1;
    }

    public JButton getSaveButton() {
        return saveButton1;
    }

    public JTextField getFieldFkLocalisation() {
        return fieldFkLocalisation;
    }






}
