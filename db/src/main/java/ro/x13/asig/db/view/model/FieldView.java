package ro.x13.asig.db.view.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class FieldView {

    private FieldType type;
    private String name;
    private String key;
    private List list;
    private int size;
    private String value;

}
