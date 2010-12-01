/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * mainFrame.java
 *
 * Created on 28 nov. 2010, 13:23:13
 */

package mvca.vue;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JPanel;
import mvca.controleur.ControleurClient;
import mvca.controleur.ControleurRadio;
import mvca.entity.Client;
import mvca.model.Mode;
import mvca.model.ModeParam;
import mvca.modeltable.TableModelClient;
import mvca.session.HibernateUtil;
import org.hibernate.Hibernate;

/**
 *
 * @author Mister B
 */
public class mainFrame extends javax.swing.JFrame {


    private ControleurRadio radioControleur;
    private HashMap<Mode, ModeParam> modeur;

    /** Creates new form mainFrame */
    public mainFrame() {
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        superJtableBD = new javax.swing.JTable();
        clientRadioButton = new javax.swing.JRadioButton();
        hotelRadioButton = new javax.swing.JRadioButton();
        restaurantRadioButton = new javax.swing.JRadioButton();
        localisationRadioButton = new javax.swing.JRadioButton();
        manifestationRadioButton = new javax.swing.JRadioButton();
        typeManifRadioButton = new javax.swing.JRadioButton();
        voyageRadioButton = new javax.swing.JRadioButton();
        reservationRadioButton = new javax.swing.JRadioButton();
        reservHotelRadioButton = new javax.swing.JRadioButton();
        reservRestauRadioButton = new javax.swing.JRadioButton();
        reservManifRadioButton = new javax.swing.JRadioButton();
        panelAmovible = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Back office");

        superJtableBD.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        superJtableBD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(superJtableBD);

        buttonGroup1.add(clientRadioButton);
        clientRadioButton.setSelected(true);
        clientRadioButton.setText("Client");
        clientRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(hotelRadioButton);
        hotelRadioButton.setText("Hotel");
        hotelRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hotelRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(restaurantRadioButton);
        restaurantRadioButton.setText("Restaurant");

        buttonGroup1.add(localisationRadioButton);
        localisationRadioButton.setText("Localisation");

        buttonGroup1.add(manifestationRadioButton);
        manifestationRadioButton.setText("Manifestation");

        buttonGroup1.add(typeManifRadioButton);
        typeManifRadioButton.setText("Type manifestation");

        buttonGroup1.add(voyageRadioButton);
        voyageRadioButton.setText("Voyage");

        buttonGroup1.add(reservationRadioButton);
        reservationRadioButton.setText("Reservation");

        buttonGroup1.add(reservHotelRadioButton);
        reservHotelRadioButton.setText("Reserv Hotel");

        buttonGroup1.add(reservRestauRadioButton);
        reservRestauRadioButton.setText("Reserv Restau");

        buttonGroup1.add(reservManifRadioButton);
        reservManifRadioButton.setText("Reserv Manif");

        panelAmovible.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout panelAmovibleLayout = new javax.swing.GroupLayout(panelAmovible);
        panelAmovible.setLayout(panelAmovibleLayout);
        panelAmovibleLayout.setHorizontalGroup(
            panelAmovibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 575, Short.MAX_VALUE)
        );
        panelAmovibleLayout.setVerticalGroup(
            panelAmovibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 228, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(typeManifRadioButton)
                    .addComponent(voyageRadioButton)
                    .addComponent(reservationRadioButton)
                    .addComponent(reservHotelRadioButton)
                    .addComponent(reservRestauRadioButton)
                    .addComponent(reservManifRadioButton)
                    .addComponent(manifestationRadioButton)
                    .addComponent(clientRadioButton)
                    .addComponent(hotelRadioButton)
                    .addComponent(restaurantRadioButton)
                    .addComponent(localisationRadioButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelAmovible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(clientRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hotelRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(restaurantRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(localisationRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(manifestationRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(typeManifRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(voyageRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reservationRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reservHotelRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reservRestauRadioButton))
                    .addComponent(panelAmovible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reservManifRadioButton)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clientRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientRadioButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clientRadioButtonActionPerformed

    private void hotelRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hotelRadioButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hotelRadioButtonActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                mainFrame mv=  new mainFrame();

            mv.init();
                mv.setVisible(true);
                


               
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton clientRadioButton;
    private javax.swing.JRadioButton hotelRadioButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton localisationRadioButton;
    private javax.swing.JRadioButton manifestationRadioButton;
    private javax.swing.JPanel panelAmovible;
    private javax.swing.JRadioButton reservHotelRadioButton;
    private javax.swing.JRadioButton reservManifRadioButton;
    private javax.swing.JRadioButton reservRestauRadioButton;
    private javax.swing.JRadioButton reservationRadioButton;
    private javax.swing.JRadioButton restaurantRadioButton;
    private javax.swing.JTable superJtableBD;
    private javax.swing.JRadioButton typeManifRadioButton;
    private javax.swing.JRadioButton voyageRadioButton;
    // End of variables declaration//GEN-END:variables

 
public void placeMachin(JPanel pane){
    this.panelAmovible.removeAll();
this.panelAmovible.repaint();
    GridBagLayout fl= new GridBagLayout();
   
    panelAmovible.setLayout(fl);
       panelAmovible.add(pane);
       GridBagConstraints constraint= new GridBagConstraints();
       constraint.fill= GridBagConstraints.BOTH;
        fl.setConstraints(pane, constraint);
       
   
   panelAmovible.revalidate();
 

}







public void setMode(Mode m){

ModeParam mp=this.modeur.get(m);
this.placeMachin(mp.getPanel());
this.superJtableBD.setModel(mp.getModel());
this.superJtableBD.removeAll();
this.superJtableBD.addMouseListener(mp.getAction());



}

    private void init() {

         jClientPane clientPane=new jClientPane();
         jHotelPane hotelPane= new jHotelPane();
         jRestaurantPane restaurantPane= new jRestaurantPane();
         jVoyagePane voyagePane=new jVoyagePane();
         jManifestationPane manifPane= new jManifestationPane();
         jTypeManifPane typeManifPane= new jTypeManifPane();
         jLocalisationPane localisationPane= new jLocalisationPane();
         jReservationHotelPane reservHotelPane= new jReservationHotelPane();
         jReservationRestaurantPane reservRestauPane= new jReservationRestaurantPane();
         jReservationManifPane reservationManifPane= new jReservationManifPane();
         jReservationnPane reservationnPane= new jReservationnPane();



    HibernateUtil<Client> em= new HibernateUtil<Client>(Client.class);
    TableModelClient gg= new TableModelClient(em);

    ControleurClient control= new ControleurClient();



    control.setEm(em);
    control.setModel(gg);
    control.setTable(this.superJtableBD);
    control.setClientControleur(clientPane);




    clientPane.getNewButton().addActionListener(control.getNewActionListener());
    clientPane.getSaveButton().addActionListener(control.getSaveActionListener());








        this.radioControleur= new ControleurRadio(this);
        this.modeur=new HashMap<Mode, ModeParam>();




        modeur.put(Mode.Client, new ModeParam(control.getTableModelListener(),gg,clientPane));
        modeur.put(Mode.Hotel, new ModeParam(null, gg, hotelPane));
        modeur.put(Mode.Restaurant, new ModeParam(null, gg, restaurantPane));
        modeur.put(Mode.Localisation, new ModeParam(null, gg, localisationPane));
        modeur.put(Mode.TypeManif, new ModeParam(null, gg, typeManifPane));
        modeur.put(Mode.Voyage, new ModeParam(null, gg, voyagePane));
        modeur.put(Mode.Manifestation, new ModeParam(null, gg, manifPane));
        modeur.put(Mode.Reservation, new ModeParam(null, gg, reservationnPane));
        modeur.put(Mode.ReservationHotel, new ModeParam(null, gg, reservHotelPane));
        modeur.put(Mode.ReservationRestaurant, new ModeParam(null, gg, reservRestauPane));
        modeur.put(Mode.ReservationManif, new ModeParam(null, gg, reservationManifPane));


        this.clientRadioButton.addActionListener(radioControleur.getClientListener());
        this.hotelRadioButton.addActionListener(radioControleur.getHotelListener());
        this.restaurantRadioButton.addActionListener(radioControleur.getRestaurantListener());
        this.reservHotelRadioButton.addActionListener(radioControleur.getReservationHotelListener());
        this.reservManifRadioButton.addActionListener(radioControleur.getReservationManifestationListener());
        this.reservationRadioButton.addActionListener(radioControleur.getReservationListener());
        this.reservRestauRadioButton.addActionListener(radioControleur.getReservationRestaurantListener());
        this.localisationRadioButton.addActionListener(radioControleur.getLocalisationListener());
        this.voyageRadioButton.addActionListener(radioControleur.getLocalisationListener());
        this.typeManifRadioButton.addActionListener(radioControleur.getTypeManifestationListener());
        this.manifestationRadioButton.addActionListener(radioControleur.getManifestationListener());


        

          
        this.setMode(Mode.Hotel);
    }

}
