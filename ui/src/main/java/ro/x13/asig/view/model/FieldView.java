package ro.x13.asig.view.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.function.BiConsumer;
import java.util.function.Function;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class FieldView {

    private String name;
    private String nameKey;
    private String clasa;

    private String key;     //when dynamic fields
    private Function<?, ?> in;
    private BiConsumer<?, ?> out;
}
