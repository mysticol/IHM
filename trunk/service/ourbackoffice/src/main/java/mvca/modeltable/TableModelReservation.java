/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mvca.modeltable;

import java.util.List;
import mvca.entity.Reservation;
import mvca.session.HibernateUtil;

/**
 *
 * @author Mister B
 */
public class TableModelReservation  extends JTableModelInterface<Reservation> {

    private List<Reservation> list;
   // private ClientJpaController entity;

    private HibernateUtil<Reservation> entityManager;

    public TableModelReservation(HibernateUtil<Reservation> em) {
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
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {

            case 0:
                return list.get(rowIndex).getReservationId();

            case 1:
                return list.get(rowIndex).getDate();



            case 2:
                return list.get(rowIndex).getFkClientId();


             case 3:
                return list.get(rowIndex).getFkReserveHotel();
                 case 4:
                return list.get(rowIndex).getFkReserveManif();

                 case 5:
                return list.get(rowIndex).getFkReserveRestau();

                 case 6:
                return list.get(rowIndex).getFkVoyage();
      

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
                return "Id client";


             case 3:
                return "Id hotel";
                 case 4:
                return "id manif";

                 case 5:
                return "id restau";

                 case 6:
                return "id voyage";
             

            default:
                return (String) "Rien";

        }

    }


    public Reservation getRows( int i) {
       return list.get(i);
    }
   @Override
    public int getIDForSelected(int i) {
        return list.get(i).getReservationId();
    }

      @Override
    public HibernateUtil<Reservation> getEm() {
       return this.entityManager;
    }
}
