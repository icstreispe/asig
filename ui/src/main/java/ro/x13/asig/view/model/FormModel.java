package ro.x13.asig.view.model;

import java.util.List;

public interface FormModel {

    /**
     * lists all the fields to be rendered on the form
     */
    List<FieldView> getFields ();

    /**
     * get the key of the title of the form
     */
    String getFormTitleKey ();

    /**
     * @return the name of the model bean
     */
    public String getModelName();


    /**
     * @return the name of the edit action
     */
    String getEditAction ();

}
