/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mvca.modeltable;

import java.util.List;
import mvca.entity.Hotel;
import mvca.session.HibernateUtil;

/**
 *
 * @author Mister B
 */
public class TableModelHotel extends JTableModelInterface<Hotel> {

    private List<Hotel> list;
   // private ClientJpaController entity;

    private HibernateUtil<Hotel> entityManager;

    public TableModelHotel(HibernateUtil<Hotel> em) {
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
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {

            case 0:
                return list.get(rowIndex).getHotelId();

            case 1:
                return list.get(rowIndex).getNomHotel();



            case 2:
                return list.get(rowIndex).getAdresse();


             case 3:
                return list.get(rowIndex).getFkLocalisationId();
                 case 4:
                return list.get(rowIndex).getNbChambre();

                 case 5:
                return list.get(rowIndex).getRankHotel();

                 case 6:
                return list.get(rowIndex).getPrix()+"€";
                 case 7:
                return list.get(rowIndex).getPrixMoyen()+"€";

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
                return "Nom";



            case 2:
                return "Adresse";


             case 3:
                return "Id loca";
                 case 4:
                return "Nb chambre";

                 case 5:
                return "Rang";

                 case 6:
                return "Prix";
                 case 7:
                return "Prix Moyen";

            default:
                return (String) "Rien";

        }

    }


    public Hotel getRows( int i) {
       return list.get(i);
    }

}
