/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mvca.model;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author Manou
 */
public class ModeParam {

    private MouseListener action;
    private AbstractTableModel model;
    private JPanel panel;

    public MouseListener getAction() {
        return action;
    }

    public void setAction(MouseListener action) {
        this.action = action;
    }

    public AbstractTableModel getModel() {
        return model;
    }

    public void setModel(AbstractTableModel model) {
        this.model = model;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public ModeParam(MouseListener action, AbstractTableModel model, JPanel panel) {
        this.action = action;
        this.model = model;
        this.panel = panel;
    }



   



}
