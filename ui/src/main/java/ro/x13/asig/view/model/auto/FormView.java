package ro.x13.asig.view.model.auto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FormView {

    private String action;
    private String baseKey;
    private boolean pagination = false;
    private String submitKey = "action.save";   //default





    public String getTitleKey (){
        return baseKey + ".form.title";
    }

    /**
     * @return the name of the model bean
     */
    public String getModelName (){
        return this.getClass().getSimpleName();
    }

/*
    private List<FieldView> fields = new ArrayList<>();

    public <T extends Domain, F extends Domain> void addCombo(String key, Function<T, F> in, BiConsumer<T, F> out) {
        FieldView field = ComboView.builder().key(key).in(in).out(out).build();
        fields.add(field);
    }

    public <T extends Domain, F extends Domain> void addString(String key, Function<T, String> in, BiConsumer<T, String> out) {
        FieldView field = ComboView.builder().key(key).in(in).out(out).build();
        fields.add(field);
    }

    public <T extends Domain> void addInteger(String key, Function<T, Integer> in, BiConsumer<T, Integer> out) {
        FieldView field = ComboView.builder().key(key).in(in).out(out).build();
        fields.add(field);
    }
 */
}
