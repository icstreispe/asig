package ro.x13.asig.db.config;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

@ControllerAdvice
public class GlobalInitializer {

    /* global InitBinder  */

    @InitBinder
    public void binder(WebDataBinder binder) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));   //sets the POSTed objects null instead of empty
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //sets the POSTed objects null instead of empty
    }


}
