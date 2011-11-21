
package genetico;

import java.util.Comparator;

/**
 *
 * @author Lorenzo Fundaró <lorenzofundaro [at] yahoo.com>
 */
public class PairComparator implements Comparator {
    
    @Override
    public int compare(Object p1, Object p2) {
        int diff = (int) (((Pair) p1).getRight() - ((Pair) p2).getRight());
        return diff;
    }
}
