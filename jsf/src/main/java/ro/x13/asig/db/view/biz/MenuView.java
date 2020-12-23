package ro.x13.asig.db.view.biz;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ro.x13.asig.db.dao.domain.meta.Action;
import ro.x13.asig.db.dao.domain.meta.ActionFlow;
import ro.x13.asig.db.dao.domain.Domain;
import ro.x13.asig.db.filter.RequestScopedController;
import ro.x13.asig.db.service.ServiceUtil;
import ro.x13.asig.db.service.meta.ActionService;
import ro.x13.asig.db.service.meta.ActionFlowService;
import ro.x13.asig.db.view.jsf.HeaderView;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Map;

@RequestScopedController("menu")
@RequiredArgsConstructor
@Getter
@Setter
public class MenuView  {


    private final ActionFlowService actionFlowService;
    private final ActionService actionService;

    private List list;

    @PostConstruct
    public void load (){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String actionCode = facesContext.getExternalContext().getRequestContextPath();
        Action action = actionService.get(actionCode);
        List<ActionFlow> actionFlowList = actionFlowService.list(action);
        list = ServiceUtil.getList(actionFlowList, this::toView);
    }

    private Map toView(Domain domain) {
        ActionFlow f = (ActionFlow) domain;
        return HeaderView.getMap(f.getEndAction().getCode(), f.getEndAction().getName());
    }


    public List getList() {
        return list;
        /*
        return HeaderView.builder()
                .add("/index.xhtml", "Acasa")
                .add( "/polita.xhtml", "Polita")
                .add("/auto/list.xhtml", "Auto")
                .build();
         */
    }

}
