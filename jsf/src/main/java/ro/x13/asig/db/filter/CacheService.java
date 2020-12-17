package ro.x13.asig.db.filter;


import org.springframework.stereotype.Component;

@Component
public class CacheService {


    public void cacheData(String key, Object value) {
        String thread = Thread.currentThread().getName();
        ContextLocal.set(key, value);
    }

    public Object getData(String key) {
        String thread = Thread.currentThread().getName();
        return ContextLocal.get(key);
    }
}
