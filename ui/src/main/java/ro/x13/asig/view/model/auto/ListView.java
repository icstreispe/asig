package ro.x13.asig.view.model.auto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.x13.asig.db.dao.domain.Domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListView  {

    //private String titleKey;
    private String baseKey;
    private List<ColumnView> columns = new ArrayList<>();
    //private List<ColumnView> headers = new ArrayList<>();


    public <T extends Domain> void addCol(String label, Function<T, ?> f) {
        addCol(label, f, true);
    }

    public <T extends Domain> void addCol(String key, Function<T, ?> f, boolean isHeader) {
        ColumnView c = new ColumnView();
        c.setKey(key);
        c.setFn(f);
        columns.add(c);
        if (isHeader) {
            c.setHeader(baseKey + "." + key);
            //headers.add(c);
        }
    }

    public void addColLink(String key, String url) {
        ColumnView c = new ColumnView();
        c.setKey(key);
        c.setAction(url);
        c.setHeader("column.header");
        columns.add(c);
        //headers.add(c);
    }

    public String getTitleKey (){
        return baseKey + ".list.title";
    }


    public Map getRow(Domain domain) {
        Map row = new HashMap();
        for (ColumnView c : this.getColumns()){
            if (!c.isLink()) {
                row.put(c.getKey(), c.getResult(domain));
            }
        }
        return row;
    }

    public int getColspan (){
        return getColumns().size();
    }


}
