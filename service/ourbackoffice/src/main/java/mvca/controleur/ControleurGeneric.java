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
import mvca.modeltable.JTableModelInterface;

import mvca.session.HibernateUtil;
import mvca.vue.EntityPane;


/**
 *
 * @author Mister B
 */
public class ControleurGeneric<E>  {

    EntityPane<E> clientControleur;
    HibernateUtil<E> em;
    JTableModelInterface<E> model;
    JTable table;




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
           
            }
        };
    }


    public MouseListener getTableModelListener(){
        return new MouseListener() {


            @Override
            public void mouseClicked(MouseEvent e) {
                clientControleur.loadEntity( model.getRows(table.getSelectedRow()));
                 //clientControleur.loadEntity(model.getRows(table.getSelectedRow()));
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

    public EntityPane<E> getClientControleur() {
        return clientControleur;
    }

    public void setClientControleur(EntityPane<E> clientControleur) {
        this.clientControleur = clientControleur;
    }

    public HibernateUtil<E> getEm() {
        return em;
    }

    public void setEm(HibernateUtil<E> em) {
        this.em = em;
    }

    public JTableModelInterface<E> getModel() {
        return model;
    }

    public void setModel(JTableModelInterface<E> model) {
        this.model = model;
    }

 

 

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }



}
