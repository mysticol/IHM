/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mvca.modeltable;


import java.util.List;
import javax.swing.table.AbstractTableModel;
import mvca.entity.Client;
import mvca.session.HibernateUtil;




/**
 *
 * @author Mister B
 */
public class TableModelClient extends JTableModelInterface<Client> {

    private List<Client> listClient;
   // private ClientJpaController entity;

    private HibernateUtil<Client> entityManager;

    public TableModelClient(HibernateUtil<Client> em) {
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
                return listClient.get(rowIndex).getClientId();

            case 1:
                return listClient.get(rowIndex).getNomClient();



            case 2:
                return listClient.get(rowIndex).getPrenomClient();
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



            case 2:
                return "Prenom";
            default:
                return (String) "Rien";

        }

    }


    public Client getRows( int i) {
       return listClient.get(i);
    }

}
