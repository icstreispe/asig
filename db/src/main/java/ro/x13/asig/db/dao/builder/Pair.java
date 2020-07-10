package ro.x13.asig.db.dao.builder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Pair<S, T> {

    private S source;
    private T target;

    public static <S, T> Pair<S, T> newPair (S source, T target) { return new Pair<S, T>(source, target);}
}
