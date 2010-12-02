/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mvca.modeltable;

import java.util.List;
import mvca.entity.Localisation;
import mvca.session.HibernateUtil;

/**
 *
 * @author Mister B
 */
public class TableModelLocalisation extends  JTableModelInterface<Localisation> {

    private List<Localisation> list;
   // private ClientJpaController entity;

    private HibernateUtil<Localisation> entityManager;

    public TableModelLocalisation(HibernateUtil<Localisation> em) {
    entityManager=em;


        this.refresh();
    }

    public void refresh() {



             list=entityManager.findAll();
             this.fireTableDataChanged();

    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {

            case 0:
                return list.get(rowIndex).getLocalisationId();

            case 1:
                return list.get(rowIndex).getPays();



            case 2:
                return list.get(rowIndex).getVille();
            default:
                return (String) "Rien";

        }
    }

    @Override
    public String getColumnName(int column) {

         switch (column) {
            case 0:
                return "ID";

            case 1:
                return "pays";



            case 2:
                return "ville";
            default:
                return (String) "Rien";

        }

    }


    public Localisation getRows( int i) {
       return list.get(i);
    }

}
