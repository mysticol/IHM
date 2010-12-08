/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mvca.tempertest;


import java.util.Map;
import java.util.TreeMap;
import javax.swing.UIManager;
import mvca.entity.Client;
import mvca.session.HibernateUtil;


import org.hibernate.Criteria;
import org.hibernate.Session;



/**
 *
 * @author Mister B
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

         UIManager.LookAndFeelInfo[] info = UIManager.getInstalledLookAndFeels();
   Map map = new TreeMap();
   for(int i=0; i<info.length;i++){
      String nomLF = info[i].getName();
      String nomClasse = info[i].getClassName();
      map.put(nomLF,nomClasse);
   }
   System.out.println( map);

          

            
    }

}
