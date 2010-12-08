/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;
import modeltable.JTableModelInterface;


/**
 *
 * @author Manou
 */
public class ModeParam {


    private JTableModelInterface model;
    private JPanel panel;



    public JTableModelInterface getModel() {
        return model;
    }

    public void setModel(JTableModelInterface model) {
        this.model = model;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public ModeParam( JTableModelInterface model, JPanel panel) {
 
        this.model = model;
        this.panel = panel;
    }



   



}
