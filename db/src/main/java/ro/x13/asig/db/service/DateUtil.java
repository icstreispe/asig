package ro.x13.asig.db.service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String format(Date d){
        SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
        return f.format(d);
    }
}
