/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mvca.modeltable;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mister B
 */
public abstract class  JTableModelInterface<E> extends AbstractTableModel {

  public abstract  void  refresh();
  public abstract E getRows( int i);

    public abstract String getColumnName(int column);
}
