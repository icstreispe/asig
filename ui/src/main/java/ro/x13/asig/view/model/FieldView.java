package ro.x13.asig.view.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class FieldView {

    private FieldType type;
    private String name;
    private String nameKey;
    private String key;
    private List list;
    private int size;
    private String value;
    private String clasa;

}
