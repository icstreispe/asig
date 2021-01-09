package ro.x13.asig.view.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntegerFieldView extends FieldView {

    //private FieldType type;

    //private List list;
    private int size;
    private Integer value;
    private String clasa;

}
