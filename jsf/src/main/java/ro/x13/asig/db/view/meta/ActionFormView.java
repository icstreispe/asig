package ro.x13.asig.db.view.meta;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ro.x13.asig.db.dao.domain.meta.Action;
import ro.x13.asig.db.dao.domain.meta.ActionFlow;
import ro.x13.asig.db.filter.RequestScopedController;
import ro.x13.asig.db.service.meta.ActionFlowService;
import ro.x13.asig.db.service.meta.ActionService;

import javax.annotation.PostConstruct;
import java.util.List;

import static ro.x13.asig.db.ViewUtil.redirect;

@RequestScopedController("actionForm")
@RequiredArgsConstructor
@Getter
@Setter
public class ActionFormView {

    private Long id;

    private Long startAction;
    private Long endAction;
    private List actionList;

    private final ActionService actionService;
    private final ActionFlowService actionFlowService;

    @PostConstruct
    public void init (){
        actionList = actionService.listCombo();
    }

    //called on loading, but also on ajax and save....
    public void setId (Long s){
        id = s;
        ActionFlow actionFlow = actionFlowService.load(id);
        toModel(actionFlow);
    }

    public String save() {
        ActionFlow auto = buildDomain();
        actionFlowService.save(auto);
        return redirect("list");
    }


    private ActionFlow buildDomain() {
        Action startAction = actionService.get(getStartAction());
        Action endAction = actionService.get(getEndAction());
        return ActionFlow.builder()
                .id(getId())
                .startAction(startAction)
                .endAction(endAction)
                .build();
    }

    private void toModel(ActionFlow actionFlow) {
        id = actionFlow.getId();
        startAction = actionFlow.getStartAction() == null ? null : actionFlow.getStartAction().getId();
        endAction = actionFlow.getEndAction() == null ? null : actionFlow.getEndAction().getId();
    }
}
