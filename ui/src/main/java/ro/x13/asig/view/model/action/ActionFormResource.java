package ro.x13.asig.view.model.action;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.x13.asig.db.dao.domain.meta.Action;
import ro.x13.asig.db.dao.domain.meta.ActionFlow;
import ro.x13.asig.db.service.meta.ActionFlowService;
import ro.x13.asig.db.service.meta.ActionService;

import static ro.x13.asig.util.ViewUtil.redirect;
import static ro.x13.asig.view.model.action.ActionListResource.ACTION_URL;

@Controller
@RequiredArgsConstructor
@RequestMapping(ACTION_URL + "/form")
public class ActionFormResource {

    private final ActionService actionService;
    private final ActionFlowService actionFlowService;


    @GetMapping(value = {"", "/{id}"})
    public String load(Model model, ActionModel actionModel) {

        ActionFlow actionFlow = actionFlowService.load(actionModel.getId());

        toModel(actionModel, actionFlow);
        getCombos(actionModel);

        model.addAttribute(actionModel.getModelName(), actionModel);
        return "admin/action.form";
    }


    @PostMapping(value = "")
    public String save(ActionModel actionModel) {
        ActionFlow actionFlow = getDomain(actionModel);
        actionFlowService.save(actionFlow);
        return redirect(ACTION_URL + "/list");
    }


    private void getCombos(ActionModel actionModel) {
        actionModel.getStartAction().setList(actionService.listCombo());
        actionModel.getEndAction().setList(actionService.listCombo());
    }

    private ActionFlow getDomain(ActionModel actionModel) {
        Action startAction = actionService.get(actionModel.getStartAction().getValue());
        Action endAction = actionService.get(actionModel.getEndAction().getValue());
        return ActionFlow.builder()
                .id(actionModel.getId())
                .startAction(startAction)
                .endAction(endAction)
                .build();
    }


    private void toModel(ActionModel actionModel, ActionFlow actionFlow) {
        actionModel.setId(actionFlow.getId());
        actionModel.getStartAction().setValue(actionFlow.getStartAction() == null ? null : actionFlow.getStartAction().getId());
        actionModel.getEndAction().setValue(actionFlow.getEndAction() == null ? null : actionFlow.getEndAction().getId());
    }
}
