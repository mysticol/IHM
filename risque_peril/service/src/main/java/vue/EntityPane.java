/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

/**
 *
 * @author Mister B
 */
public interface EntityPane<E> {


public E getEntity();
public void loadEntity( E entity);
public void clearField();

    
}
