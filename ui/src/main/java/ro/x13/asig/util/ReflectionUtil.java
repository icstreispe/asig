package ro.x13.asig.util;

import ro.x13.asig.view.model.FieldView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtil {

    /**
     * @return all fields whose type implements the interface passed as class
     */
    public static <I> List<I> getFieldsByType(Object o, Class<I> clazz)  {
        List fields = new ArrayList();
        try {
            for (Field f : o.getClass().getDeclaredFields()) {
                if (clazz.isAssignableFrom(f.getType())) {
                    f.setAccessible(true);  //for accessing private fields
                    fields.add(f.get(o));

                }
            }
        } catch (IllegalAccessException e){
            throw new RuntimeException(e);
        }
        return fields;
    }

    public static <I> List<Field> getFieldsMetaByType(Object o, Class<I> clazz)  {
        List fields = new ArrayList();
            for (Field f : o.getClass().getDeclaredFields()) {
                if (clazz.isAssignableFrom(f.getType())) {
                    f.setAccessible(true);  //for accessing private fields
                    fields.add(f);

                }
            }

        return fields;
    }

    /**
     * create inner fields of type FieldView for the object o
     */
    public static void createFields(String base, Object o) {
        try {
            for (Field f : getFieldsMetaByType(o, FieldView.class)) {
                FieldView field = (FieldView) f.getType().getDeclaredConstructor().newInstance();   //(FieldView) f.get(this);
                field.setName(f.getName());
                field.setNameKey(base + f.getName());
                f.set(o, field);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
