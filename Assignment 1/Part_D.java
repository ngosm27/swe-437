import java.util.Vector;

public class Part_D {

    public static void main(String[] args) {
        Vector<Integer> a = new Vector<>();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(5);
        a.add(2);

        Vector<Integer> b = new Vector<>();
        b.add(4);
        b.add(2);

        Vector<Integer> res = union(a, b);
        for (Integer x : res) {
            System.out.print(x + ", ");
        }
    }

    // re-defined method signature to clarify types
    public static <E> Vector<E> union(Vector<E> a, Vector<E> b) {
        Vector<E> res = new Vector<>();

        // return if a or b is null
        if (a == null || b == null)
            return null;

        // copy all elements in vector 'a' into vector 'res'; ignore dupes
        for (int i = 0; i < a.size(); i++) {
            if (!res.contains(a.elementAt(i))) {
                res.add(a.elementAt(i));
            }
        }
        // copy all elements in vector 'b' into vector 'res'; ignore dupes
        for (int i = 0; i < b.size(); i++) {
            if (!res.contains(b.elementAt(i))) {
                res.add(b.elementAt(i));
            }
        }

        return res;
    }
}