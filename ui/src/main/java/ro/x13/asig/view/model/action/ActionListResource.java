package ro.x13.asig.view.model.action;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.x13.asig.db.dao.domain.Domain;
import ro.x13.asig.db.dao.domain.meta.ActionFlow;
import ro.x13.asig.db.service.ServiceUtil;
import ro.x13.asig.db.service.meta.ActionFlowService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static ro.x13.asig.util.ViewUtil.redirect;
import static ro.x13.asig.view.model.action.ActionListResource.ACTION_URL;


@Controller
@RequiredArgsConstructor
@RequestMapping(ACTION_URL + "/list")
public class ActionListResource {

    public static final String ACTION_URL = "/action";

    private List list;

    @Autowired
    private ActionFlowService autoFlowService;


    @RequestMapping(value = "", method = {GET, POST})
    public String load(Model model, ActionModel actionModel) {

        ActionFlow actionFlow = getDomain(actionModel);
        Page<ActionFlow> actionFlowList = autoFlowService.listAll(actionFlow, actionModel.getPage(), 7);
        list = ServiceUtil.getList(actionFlowList, this::getView);
        actionModel.setDataList(list);


        getCombos(actionModel);

        model.addAttribute(actionModel.getModelName(), actionModel);
        return "admin/action.list";
    }

    @GetMapping(value = "/del/{id}")
    public String del(ActionModel actionModel) {
        autoFlowService.del(actionModel.getId());
        return redirect(ACTION_URL + "/list");
    }

    private void getCombos(ActionModel autoModel) {

    }


    private ActionFlow getDomain(ActionModel autoModel) {

        return new ActionFlow();
    }

    private Map getView(Domain domain) {
        ActionFlow actionFlow = (ActionFlow) domain;
        Map m = new HashMap();
        m.put("id", actionFlow.getId());
        m.put("startAction", actionFlow.getStartAction() == null ? null : actionFlow.getStartAction().getName());
        m.put("endAction", actionFlow.getEndAction() == null ? null : actionFlow.getEndAction().getName());

        return m;
    }


}
