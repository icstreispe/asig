package ro.x13.asig.db.view.jsf;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class HeaderView {

    private List list;

    public static HeaderView builder(){
        HeaderView h = new HeaderView();
        h.setList(new ArrayList<>());
        return h;
    }

    public HeaderView add (String k, String l){
        list.add (getMap(k, l));
        return this;
    }

    public List build(){
        return list;
    }

    public static Map getMap(String k, String l) {
        Map m = new HashMap();
        m.put ("key", k);
        m.put ("label", l);
        return m;
    }
}
