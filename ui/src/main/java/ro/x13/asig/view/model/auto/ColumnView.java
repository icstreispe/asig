package ro.x13.asig.view.model.auto;


import lombok.Data;
import ro.x13.asig.db.dao.domain.CatalogDomain;
import ro.x13.asig.db.dao.domain.Domain;

import java.util.Date;
import java.util.function.Function;

@Data
public class ColumnView {
    private String header;
    private String key;     //dual use: keye mesaj pt label link & keye pt date din map
    private String action;
    private Function fn;
    private Type type;

    public Object getResult(Domain d) {
        Object o = fn.apply(d);
        if (o == null) {
            return null;
        } else if (o instanceof String) {
            return o;
        } else if (o instanceof Long) {
            return o;
        } else if (o instanceof Integer) {
            return o;
        } else if (o instanceof Date) {
            return o;
        } else if (o instanceof CatalogDomain) {
            return ((CatalogDomain) fn.apply(d)).getName();
        }
        return "?";
    }

    public boolean isLink (){
        return action != null;
    }

    public boolean isHeader (){
        return header != null;
    }

    public boolean isData (){
        return header != null && action == null;
    }

    enum Type {
        CATALOG, BASIC
    }
}
