package unUtils;

import java.util.Comparator;

/**
 * Class use to compare label string, usefull to order label
 * from the shortest to the longuest
 * 
 * @author mfortun
 *
 */
public class LabelComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {

        int sizo1 = o1.length();
        int sizo2 = o2.length();

        return ((Integer) sizo1).compareTo(sizo2);

    }

}
