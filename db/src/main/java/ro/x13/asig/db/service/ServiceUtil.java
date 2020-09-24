package ro.x13.asig.db.service;

import ro.x13.asig.db.dao.domain.*;
import ro.x13.asig.db.view.model.TextValueModel;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServiceUtil {

    public static <T extends CatalogDomain> List<TextValueModel> getTextValueModelList(List<T> domainList) {
        List<TextValueModel> list = domainList.stream()
                .map(s -> TextValueModel.builder()
                        .text(s.getName())
                        .value("" + s.getId())
                        .build())
                .collect(Collectors.toList());
        list.add(0, TextValueModel.builder().text("-").value("").build());
        return list;
    }

    public static <T> List<TextValueModel> getTextValueModelList(List<T> domainList, Function<? super T, TextValueModel> f) {
        List<TextValueModel> list = domainList.stream()
                .map(f)
                .collect(Collectors.toList());
        list.add(0, TextValueModel.builder().text("-").value("").build());
        return list;
    }

    public  static List<Map> getList(List<? extends Domain> list, Function<Domain, Map> mapper) {
        return StreamSupport.stream(list.spliterator(), false)
                .map(mapper)       // pt list(lista()): Asig::toMap
                .collect(Collectors.toList());
    }



}
