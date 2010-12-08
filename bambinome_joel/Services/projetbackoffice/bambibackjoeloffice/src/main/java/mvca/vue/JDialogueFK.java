/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JDialogueFK.java
 *
 * Created on 2 d�c. 2010, 21:37:48
 */

package mvca.vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import mvca.modeltable.JTableModelInterface;

/**
 *
 * @author Mister B
 */
public class JDialogueFK extends javax.swing.JDialog {

    private JTableModelInterface model;
    private JTextField cible;



    /** Creates new form JDialogueFK */
    public JDialogueFK(java.awt.Frame parent , JTableModelInterface mod,JTextField cibl ) {
        super(parent, true);
        initComponents();
        this.model= mod;
        this.cible=cibl;
        this.jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.jTable1.setModel(model);
        this.okButton.addActionListener(actionListener());
        this.setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        okButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        okButton.setText("ok");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Choix de l'enregistrement r�f�renc� par l'ID :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(okButton)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(okButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables




    private ActionListener actionListener(){
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
              cible.setText(String.valueOf(model.getIDForSelected(jTable1.getSelectedRow())));
              }catch (Exception a){
                  cible.setText("");
              }
                setVisible(false);
            }
        };
    }


}
