package ro.x13.asig.view.model.auto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import ro.x13.asig.util.ReflectionUtil;
import ro.x13.asig.view.model.*;

import java.util.List;

import static ro.x13.asig.view.model.auto.AutoListResource.AUTO_URL;

@Data
@SuperBuilder
//@NoArgsConstructor
@AllArgsConstructor
public class AutoFormModel implements FormModel {


    private Long id;

    //name & key decis de reflection
    private ComboView marca;
    private StringFieldView model;
    private ComboView categorieAuto;
    private ComboView tipAuto;
    private ComboView stareMatric;
    private StringFieldView nrMatric;
    private StringFieldView serieCiv;
    private StringFieldView serieSasiu;
    private IntegerFieldView putere;
    private IntegerFieldView cilindree;
    private IntegerFieldView masaMax;
    private IntegerFieldView nrLocuri;
    private IntegerFieldView anFabricatie;
    private ComboView combustibil;
    private ComboView utilizare;

    private static FormView form;

    static {
        form = new FormView();
        form.setBaseKey("auto");
        form.setAction(AUTO_URL + "/form");
        //form.addCombo ("tipAuto", Auto::getTipAuto, Auto::setTipAuto);
        //form.addString("serieCiv", Auto::getSerieCiv, Auto::setSerieCiv);
        //form.addInteger("masaMax", Auto::getMasaMax, Auto::setMasaMax);
    }

    public FormView getForm (){
        return form;
    }

    //-----------------------------------------------------
    public AutoFormModel() {
        ReflectionUtil.initFields("auto.",this);    //TODO particular key
    }

    public List<FieldView> getFormFields()  {
        return ReflectionUtil.getFieldsByType(this, FieldView.class);
    }

    /**
     * @return the name of the model bean
     */
    public String getModelName (){
        return this.getClass().getSimpleName();
    }

}
