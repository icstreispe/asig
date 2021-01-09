package ro.x13.asig.view.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComboView extends FieldView {

    //private FieldType type;
    //private String key;
    private List list;
    //private int size;
    private Long value;


}
