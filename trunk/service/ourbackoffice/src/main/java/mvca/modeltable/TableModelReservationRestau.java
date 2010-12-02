/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mvca.modeltable;

import java.util.List;
import mvca.entity.ReservationRestau;
import mvca.session.HibernateUtil;

/**
 *
 * @author Mister B
 */
public class TableModelReservationRestau extends JTableModelInterface<ReservationRestau> {

    private List<ReservationRestau> listClient;
   // private ClientJpaController entity;

    private HibernateUtil<ReservationRestau> entityManager;

    
public TableModelReservationRestau(HibernateUtil<ReservationRestau> em) {
    entityManager=em;

        this.refresh();
    }

    public void refresh() {



             listClient=entityManager.findAll();
             this.fireTableDataChanged();

    }

    @Override
    public int getRowCount() {
        return listClient.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {

            case 0:
                return listClient.get(rowIndex).getReservationRestauId();

            case 1:
                return listClient.get(rowIndex).getDate();



            case 2:
                return listClient.get(rowIndex).getFkIdClient();

                case 3:
                return listClient.get(rowIndex).getFkRestauId();
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
                return "Date";



            case 2:
                return "id client";

                  case 3:
                return "id restau";
            default:
                return (String) "Rien";

        }

    }


    public ReservationRestau getRows( int i) {
       return listClient.get(i);
    }
}
