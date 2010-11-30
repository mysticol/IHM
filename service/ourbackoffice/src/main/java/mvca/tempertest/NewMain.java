/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tempertest;


import mvca.entity.Client;

import mvca.session.NewHibernateUtil;
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


        NewHibernateUtil hb = new NewHibernateUtil();

       Session ss= hb.getSessionFactory().openSession();

          Criteria criteria = ss.createCriteria(Client.class);


             System.out.println(criteria.list());

          

            
    }

}
