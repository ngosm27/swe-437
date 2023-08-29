import java.util.Vector;

public class Part_A {

    public static void main(String[] args) {
        Vector<Integer> a = new Vector<>();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(5);
        a.add(2);

        Vector<String> b = new Vector<>();
        b.add("4");
        b.add("2");

        Vector res = union(a, b);
        for (Object x : res) {
            System.out.print(x + ", ");
        }
    }

    public static Vector union(Vector a, Vector b) {
        Vector res = new Vector();

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