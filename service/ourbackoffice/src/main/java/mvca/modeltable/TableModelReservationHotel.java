/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mvca.modeltable;

import java.util.List;
import mvca.entity.ReservationHotel;
import mvca.session.HibernateUtil;

/**
 *
 * @author Mister B
 */
public class TableModelReservationHotel extends JTableModelInterface<ReservationHotel> {

    private List<ReservationHotel> listClient;
   // private ClientJpaController entity;

    private HibernateUtil<ReservationHotel> entityManager;


public TableModelReservationHotel(HibernateUtil<ReservationHotel> em) {
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
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {

            case 0:
                return listClient.get(rowIndex).getReservationHotelId();

            case 1:
                return listClient.get(rowIndex).getDate();



            case 2:
                return listClient.get(rowIndex).getFkIdClient();

                case 3:
                return listClient.get(rowIndex).getFkHotelId();
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
                return "id hotel";
            default:
                return (String) "Rien";

        }

    }


    public ReservationHotel getRows( int i) {
       return listClient.get(i);
    }
   @Override
    public int getIDForSelected(int i) {
        return listClient.get(i).getReservationHotelId();
    }
}

