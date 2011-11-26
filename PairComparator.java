
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
        double diff = (((Pair) p2).getRight() - ((Pair) p1).getRight());
        if (diff > 0.0) return 1;
        else if (diff == 0.0) return 0;
        else return -1;
    }
}