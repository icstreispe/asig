package ro.x13.asig.db.view.meta;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ro.x13.asig.db.dao.domain.Domain;
import ro.x13.asig.db.dao.domain.meta.ActionFlow;
import ro.x13.asig.db.filter.RequestScopedController;
import ro.x13.asig.db.service.ServiceUtil;
import ro.x13.asig.db.service.meta.ActionFlowService;
import ro.x13.asig.db.service.meta.ActionService;
import ro.x13.asig.db.view.ListView;
import ro.x13.asig.db.view.jsf.HeaderView;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ro.x13.asig.db.ViewUtil.redirect;

@RequestScopedController("actionList")
@RequiredArgsConstructor
@Getter
@Setter
public class ActionListView extends PagingView implements ListView {

    private static final List headers = HeaderView.builder()
            .add("startAction", "")
            .add("endAction", "")
            .build();

    private List list;

    private final ActionFlowService autoFlowService;
    private final ActionService actionService;


    @PostConstruct
    public void init(){
        load();
    }

    @Override
    protected void load(){
        Iterable<ActionFlow> actionFlowList = autoFlowService.listAll(new ActionFlow(), getPage(), getRowPerPage());
        list = ServiceUtil.getList(actionFlowList, this::toView);
    }


    @Override
    public String getName(){
        return "action.";
    }

    @Override
    public List getList() {
        return  list;
    }

    @Override
    public List getHeaders() {
        return headers;
    }


    @Override
    public String del(Long id) {
        autoFlowService.del(id);
        return redirect("list");
    }

    private Map toView(Domain domain) {
        ActionFlow actionFlow = (ActionFlow) domain;
        Map m = new HashMap();
        m.put("id", actionFlow.getId());
        m.put("startAction", actionFlow.getStartAction() == null ? null : actionFlow.getStartAction().getName());
        m.put("endAction", actionFlow.getEndAction() == null ? null : actionFlow.getEndAction().getName());

        return m;
    }

}
