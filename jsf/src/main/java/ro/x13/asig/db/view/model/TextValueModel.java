package ro.x13.asig.db.view.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TextValueModel {

    public static final String cName = "ro.x13.asig.db.view.model.TextValueModel";

    public String text;
    public String value;

    public TextValueModel(Long value, String text){
        this.text = text;
        this.value = String.valueOf(value);
    }
}
