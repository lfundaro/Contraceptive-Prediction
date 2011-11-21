
package genetico;

import java.util.Comparator;

/**
 *
 * @author Lorenzo Fundaró <lorenzofundaro [at] yahoo.com>
 */
public class PairComparator implements Comparator {
    
    // Comparador de orden descenciente.
    @Override
    public int compare(Object p1, Object p2) {
        int diff = (int) (((Pair) p2).getRight() - ((Pair) p1).getRight());
        return diff;
    }
}
