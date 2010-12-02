/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mvca.modeltable;

import java.util.List;
import mvca.entity.Manifestation;
import mvca.session.HibernateUtil;

/**
 *
 * @author Mister B
 */
public class TableModelManifestation extends JTableModelInterface<Manifestation> {

    private List<Manifestation> list;
   // private ClientJpaController entity;

    private HibernateUtil<Manifestation> entityManager;

    public TableModelManifestation(HibernateUtil<Manifestation> em) {
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
                return list.get(rowIndex).getManifestationId();

            case 1:
                return list.get(rowIndex).getNomManifestation();



            case 2:
                return list.get(rowIndex).getAdresse();


             case 3:
                return list.get(rowIndex).getFkLocalisationId();
                 case 4:
                return list.get(rowIndex).getNbPlace();

                 case 5:
                return list.get(rowIndex).getFkIdType();

                 case 6:
                return list.get(rowIndex).getDate();
                 case 7:
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
                return "Nb place";

                 case 5:
                return "Type";

                 case 6:
                return "Date";
                 case 7:
                return "Prix ";

            default:
                return (String) "Rien";

        }

    }


    public Manifestation getRows( int i) {
       return list.get(i);
    }

}
