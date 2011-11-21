
package genetico;

/**
 *
 * @author Lorenzo Fundaró <lorenzofundaro [at] yahoo.com>
 */
public class Pair {
    int left;
    double right;
    
    public Pair(int left, double right) {
        this.left = left;
        this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public double getRight() {
        return right;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public void setRight(double right) {
        this.right = right;
    }

    @Override
    protected Pair clone() {
        Pair n = new Pair(this.left, this.right);
        return n;
    }
    
    @Override
    public String toString() {
        return "Pair{" + "left=" + left + ", right=" + right + '}';
    }
    
    
}
