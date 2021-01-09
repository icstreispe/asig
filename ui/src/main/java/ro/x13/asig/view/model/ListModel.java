package ro.x13.asig.view.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.x13.asig.util.ReflectionUtil;

import java.util.List;

@NoArgsConstructor
@SuperBuilder
@Data
public abstract class ListModel {

    private Integer page = 0;

    //moved to view
    //public abstract String[] getHeaders ();

    public List<FieldView> getFormFields()  {
        return ReflectionUtil.getFieldsByType(this, FieldView.class);
    }

}
