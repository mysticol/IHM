/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mvca.modeltable;

import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import mvca.entity.Client;
import mvca.session.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;



/**
 *
 * @author Mister B
 */
public class TableModelClient extends AbstractTableModel {

    private List<Client> listClient;
   // private ClientJpaController entity;

    public TableModelClient() {


        //this.entity = new ClientJpaController();
        this.refresh();
    }

    public void refresh() {
         HibernateUtil<Client> hb = new HibernateUtil<Client>(Client.class);

      Client cl =new Client();
         cl.setNomClient("joel");
         cl.setPrenomClient("ponay");

   hb.insert(cl);
       hb.delete(3);//  ;
Client temp=hb.findById(4);
temp.setNomClient("connard ");
hb.update(temp);
   System.out.println( );
             listClient=hb.findAll();
      //  listClient = entity.findClientEntities();

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



}
