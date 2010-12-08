/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modeltable;

import java.util.List;
import pojos.ReservationManif;
import session.HibernateUtil;

/**
 *
 * @author Mister B
 */
public class TableModelReservationManif extends JTableModelInterface<ReservationManif> {

    private List<ReservationManif> listClient;
   // private ClientJpaController entity;

    private HibernateUtil<ReservationManif> entityManager;


public TableModelReservationManif(HibernateUtil<ReservationManif> em) {
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
                return listClient.get(rowIndex).getReservationManifId();

            case 1:
                return listClient.get(rowIndex).getDate();



            case 2:
                return listClient.get(rowIndex).getFkIdClient();

                case 3:
                return listClient.get(rowIndex).getFkIdManif();
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
                return "id manif";
            default:
                return (String) "Rien";

        }

    }


    public ReservationManif getRows( int i) {
       return listClient.get(i);
    }
   @Override
    public int getIDForSelected(int i) {
        return listClient.get(i).getReservationManifId();
    }

      @Override
    public HibernateUtil<ReservationManif> getEm() {
       return this.entityManager;
    }
}
