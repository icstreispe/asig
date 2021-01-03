package ro.x13.asig.view.model.action;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.x13.asig.view.model.ComboView;
import ro.x13.asig.view.model.FormModel;
import ro.x13.asig.view.model.ListModel;

import java.util.List;

import static ro.x13.asig.view.model.action.ActionListResource.ACTION_URL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActionModel extends ListModel implements FormModel {

    private static final String MODEL_NAME = "action";
    private Long id;
    private List dataList;

    //TODO name & key decis de reflection
    private ComboView startAction = ComboView.builder().nameKey("action.startAction").name("startAction").build();
    private ComboView endAction = ComboView.builder().nameKey("action.endAction").name("endAction").build();


    public String[] getHeaders (){
        return new String[] {"startAction", "endAction"};
    }

    public String getListTitleKey (){
        return "action.list.title";
    }

    public String getBaseKey (){
        return "action";
    }

    public String getEditAction (){
        return ACTION_URL + "/form";
    }

    public String getDelAction (){
        return ACTION_URL + "/list/del";
    }

    public String getFormTitleKey (){
        return "action.form.title";
    }

    public String getModelName (){
        return MODEL_NAME;
    }

}
