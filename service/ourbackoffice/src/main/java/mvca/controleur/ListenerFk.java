/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mvca.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;
import mvca.modeltable.JTableModelInterface;
import mvca.vue.JDialogueFK;

/**
 *
 * @author Mister B
 */
public class ListenerFk implements ActionListener{

    private JFrame f;
    private JTableModelInterface model;
    private JTextField field;

    public ListenerFk(JFrame f, JTableModelInterface model, JTextField field) {
        this.f = f;
        this.model = model;
        this.field = field;
    }

    


    @Override
    public void actionPerformed(ActionEvent e) {
        new JDialogueFK(f, model, field);
    }




}
