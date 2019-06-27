package effectivejava.item10;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Broken - violates symmetry!  (Page 39)
public final class CaseInsensitiveString {
    private final String s;

    public CaseInsensitiveString(String s) {
        this.s = Objects.requireNonNull(s);
    }

    // Broken - violates symmetry!
    @Override
    public boolean equals(Object o) {
        if (o instanceof CaseInsensitiveString) {
            return s.equalsIgnoreCase(
                ((CaseInsensitiveString) o).s);
        }
        if (o instanceof String)  // One-way interoperability!
        { return s.equalsIgnoreCase((String) o); }
        return false;
    }

    // Demonstration of the problem (Page 40)
    public static void main(String[] args) {
        CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
        String s = "polish";

        System.out.println(cis.equals(s)); // true
        System.out.println(s.equals(cis)); // false (String s equals)

        List<CaseInsensitiveString> list = new ArrayList<>();
        list.add(cis);

        // JDK버전에 따라 다를 수 있다. x.equals(y)로 비교할 수 도 있고, y.equals(x)로 비교될 수 있기 때문이다.
        System.out.println(list.contains(s)); // false
    }

//    // Fixed equals method (Page 40)

//    위의 내용을 수정한다면, String과의 비교는 포기해야 한다.
//    같은 CaseInsensitiveString 타입인 경우에만 비교하도록 한다.
//    @Override public boolean equals(Object o) {
//        return o instanceof CaseInsensitiveString &&
//                ((CaseInsensitiveString) o).s.equalsIgnoreCase(s);
//    }
}
