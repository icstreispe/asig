package ro.x13.asig.view.model.auto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;
import ro.x13.asig.db.dao.domain.Auto;
import ro.x13.asig.db.dao.domain.Domain;
import ro.x13.asig.db.service.ServiceUtil;
import ro.x13.asig.util.ReflectionUtil;
import ro.x13.asig.view.model.ListModel;
import ro.x13.asig.view.model.StringFieldView;

import java.util.List;
import java.util.Map;

import static ro.x13.asig.view.model.auto.AutoListResource.AUTO_URL;

@Data
@Builder
//@NoArgsConstructor
@AllArgsConstructor
public class AutoListModel extends ListModel  {

    private Long id;


    //name & key decis de reflection
    private StringFieldView serieCiv;


    public AutoListModel() {
        ReflectionUtil.initFields("auto.",this);    //TODO auto.
    }

    private static FormView form;

    static {
        form = new FormView();
        form.setBaseKey("auto");
        form.setAction(AUTO_URL + "/list");
        form.setPagination(true);
        form.setSubmitKey("action.view");
    }

    public FormView getForm (){
        return form;
    }


    /**
     * @return the name of the model bean
     */
    public String getModelName (){
        return this.getClass().getSimpleName();
    }

//-----------------------------------------------------------------------------------------------

    private List<Map> dataList;

    private static ListView list;

    static {
        list = new ListView();
        list.setBaseKey("auto");
        list.addColLink("action.edit", AUTO_URL + "/form");
        list.addCol("id", Auto::getId, false);
        list.addCol("model", Auto::getModel);
        list.addCol("marca", Auto::getMarca);
        list.addCol("categorieAuto", Auto::getCategorieAuto);
        list.addCol("stareMatric", Auto::getStareMatric);
        list.addCol("nrMatric", Auto::getNrLocuri);
        list.addCol("serieCiv", Auto::getSerieCiv);
        list.addCol("serieSasiu", Auto::getSerieSasiu);
        list.addCol("putere", Auto::getPutere);
        list.addCol("cilindree", Auto::getCilindree);
        list.addCol("masaMax", Auto::getMasaMax);
        list.addCol("nrLocuri", Auto::getNrLocuri);
        list.addCol("anFabricatie", Auto::getAnFabricatie);
        list.addCol("combustibil", Auto::getCombustibil);
        list.addCol("utilizare", Auto::getUtilizare);
        //list.addColLink("action.del", AUTO_URL + "/form");
    }

    public ListView getList (){
        return list;
    }

    public void setDataList(Page<? extends Domain> domainList){
        dataList  = ServiceUtil.getList(domainList, list::getRow);
    }

}
