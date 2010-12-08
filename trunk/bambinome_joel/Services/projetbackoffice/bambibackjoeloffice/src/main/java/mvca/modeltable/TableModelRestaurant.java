/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mvca.modeltable;

import java.util.List;
import mvca.entity.Restaurant;
import mvca.session.HibernateUtil;

/**
 *
 * @author Mister B
 */
public class TableModelRestaurant extends JTableModelInterface<Restaurant> {

    private List<Restaurant> list;
   // private ClientJpaController entity;

    private HibernateUtil<Restaurant> entityManager;

    public TableModelRestaurant(HibernateUtil<Restaurant> em) {
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
                return list.get(rowIndex).getRestaurantId();

            case 1:
                return list.get(rowIndex).getNomRestaurant();



            case 2:
                return list.get(rowIndex).getAdresse();


             case 3:
                return list.get(rowIndex).getFkLocalisationId();
                 case 4:
                return list.get(rowIndex).getNbCouverts();

                 case 5:
                return list.get(rowIndex).getRateRestaurant();

                 case 6:
                return list.get(rowIndex).getPrix()+"€";
               

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
                return "Nb couvert";

                 case 5:
                return "Rang";

                 case 6:
                return "Prix";
              

            default:
                return (String) "Rien";

        }

    }


    public Restaurant getRows( int i) {
       return list.get(i);
    }
   @Override
    public int getIDForSelected(int i) {
        return list.get(i).getRestaurantId();
    }

      @Override
    public HibernateUtil<Restaurant> getEm() {
       return this.entityManager;
    }
}
