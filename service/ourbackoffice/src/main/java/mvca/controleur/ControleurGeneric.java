/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mvca.controleur;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

 

 

    



}
