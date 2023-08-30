import java.util.HashSet;
import java.util.Set;

public class Part_D {

    public static void main(String[] args) {
        Set<Integer> a = new HashSet<>();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(5);
        a.add(2);

        Set<Integer> b = new HashSet<>();
        b.add(4);
        b.add(2);

        Set<Integer> res = union(a, b);
        for (Integer x : res) {
            System.out.print(x + ", ");
        }
    }

    // re-defined method signature to clarify types
    public static <E> Set<E> union(Set<E> a, Set<E> b) {
        Set<E> res = new HashSet<>();

        // return if a and b is null
        if (a == null || b == null)
            return null;

        // copy all elements in Set 'a' into Set 'res'
        for (E e : a) {
            res.add(e);
        }

        // copy all elements in Set 'b' into Set 'res'
        for (E e : b) {
            res.add(e);
        }

        return res;
    }
}