package study.interview;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Maptest {
    public static void main(String[] args) {
        PrintStream ps;
        String b1;
        Map m = new HashMap();
        User u1 = new User("allen");
        m.put(u1,"allen");


        User u2 = new User("alex");
        m.put(u2,"alex");
        System.out.println(u1 == u2);
      System.out.println(m);
        Thread a;
        ThreadLocal b;
    }
}

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return o.hashCode() == this.hashCode();
    }

    @Override
    public int hashCode() {
        return 1;
    }


}
