package ro.x13.asig.db.filter;

import java.util.HashMap;
import java.util.Map;

public class ContextLocal {

    private static ThreadLocal<Map> all = new ThreadLocal();

    public static void set(Object key, Object value) {
        getMap().put(key, value);
    }

    public static Object get(Object key) {
        return getMap().get(key);
    }

    private static Map getMap() {
        Map m = all.get();
        if (m == null) {
            m = new HashMap();
            all.set(m);
        }
        return m;
    }
}
