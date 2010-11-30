/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mvca.controleur;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;
import mvca.entity.Client;
import mvca.modeltable.TableModelClient;

import mvca.session.HibernateUtil;
import mvca.vue.jClientPane;

/**
 *
 * @author Mister B
 */
public class controleurClient {

    jClientPane clientControleur;
    HibernateUtil<Client> em;
    TableModelClient model;
    JTable table;



    public ActionListener getEditActionListener(){
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            clientControleur.editEntity();
            }
        };

    }

    public ActionListener getSaveActionListener(){
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            em.insertOrUpdate(clientControleur.getEntity());
            model.refresh();
            }
        };

    }
    

    public ActionListener getNewActionListener(){
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            clientControleur.clearField();
            clientControleur.editEntity();
            }
        };
    }


    public MouseListener getTableModelListener(){
        return new MouseListener() {


            @Override
            public void mouseClicked(MouseEvent e) {
                 clientControleur.loadEntity(model.getRows(table.getSelectedRow()));
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
               
            }
        };


    }

    public jClientPane getClientControleur() {
        return clientControleur;
    }

    public void setClientControleur(jClientPane clientControleur) {
        this.clientControleur = clientControleur;
    }

    public HibernateUtil<Client> getEm() {
        return em;
    }

    public void setEm(HibernateUtil<Client> em) {
        this.em = em;
    }

    public TableModelClient getModel() {
        return model;
    }

    public void setModel(TableModelClient model) {
        this.model = model;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }



}
