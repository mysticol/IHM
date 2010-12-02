/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mvca.modeltable;

import java.util.List;
import mvca.entity.Voyage;
import mvca.session.HibernateUtil;

/**
 *
 * @author Mister B
 */
public class TableModelVoyage extends JTableModelInterface<Voyage> {

    private List<Voyage> list;
   // private ClientJpaController entity;

    private HibernateUtil<Voyage> entityManager;

    public TableModelVoyage(HibernateUtil<Voyage> em) {
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
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {

            case 0:
                return list.get(rowIndex).getVoyageId();

            case 1:
                return list.get(rowIndex).getDate();



            case 2:
                return list.get(rowIndex).getFkLocalisationArrive();

                case 3:
                return list.get(rowIndex).getFkLocalisationDepart();

                case 4:
                    return list.get(rowIndex).getFkClientId();

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
                return "date";


        case 2:
                return "id Arrive";

                case 3:
                return "id Depart";

                case 4:
                    return "id client";
            default:
                return (String) "Rien";

        }

    }


    public Voyage getRows( int i) {
       return list.get(i);
    }
   @Override
    public int getIDForSelected(int i) {
        return list.get(i).getVoyageId();
    }
}
