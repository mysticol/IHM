/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mvca.modeltable;

import java.util.List;
import mvca.entity.TypeManifestation;
import mvca.session.HibernateUtil;

/**
 *
 * @author Mister B
 */
public class TableModelTypeManif extends JTableModelInterface<TypeManifestation> {

    private List<TypeManifestation> list;
   // private ClientJpaController entity;

    private HibernateUtil<TypeManifestation> entityManager;

    public TableModelTypeManif(HibernateUtil<TypeManifestation> em) {
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
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {

            case 0:
                return list.get(rowIndex).getTypeId();

            case 1:
                return list.get(rowIndex).getNomType();



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
                return "nom";


            default:
                return (String) "Rien";

        }

    }


    public TypeManifestation getRows( int i) {
       return list.get(i);
    }
   @Override
    public int getIDForSelected(int i) {
        return list.get(i).getTypeId();
    }
}