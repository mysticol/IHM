/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modeltable;

import javax.swing.table.AbstractTableModel;
import session.HibernateUtil;

/**
 *
 * @author Mister B
 */
public abstract class  JTableModelInterface<E> extends AbstractTableModel {

  public abstract  void  refresh();
  public abstract E getRows( int i);

    public abstract String getColumnName(int column);
    public abstract int getIDForSelected(int i);
    public abstract HibernateUtil<E> getEm();
}
