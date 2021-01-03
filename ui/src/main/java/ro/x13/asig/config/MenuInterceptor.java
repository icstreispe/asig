package ro.x13.asig.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import ro.x13.asig.db.dao.domain.Domain;
import ro.x13.asig.db.dao.domain.meta.Action;
import ro.x13.asig.db.dao.domain.meta.ActionFlow;
import ro.x13.asig.db.service.ServiceUtil;
import ro.x13.asig.db.service.meta.ActionFlowService;
import ro.x13.asig.db.service.meta.ActionService;
import ro.x13.asig.db.view.model.TextValueModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MenuInterceptor implements HandlerInterceptor {

    @Autowired
    private ActionFlowService actionFlowService;

    @Autowired
    private ActionService actionService;

    @Override
    public boolean preHandle
            (HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        System.out.println("Pre Handle method [" + request.getRequestURI() + "]");

        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
//TODO filter out resources to avoid select
        Action action = actionService.get(request.getRequestURI());
        List list = new ArrayList();
        if (action != null) {
            List<ActionFlow> actionFlowList = actionFlowService.list(action);
            list = ServiceUtil.getList(actionFlowList, this::toView);
        }
        request.setAttribute("menu", list);

        System.out.println("Post Handle method [" + request.getRequestURI() + "]");
    }
    @Override
    public void afterCompletion
            (HttpServletRequest request, HttpServletResponse response, Object
                    handler, Exception exception) throws Exception {

        System.out.println("Response completed [" + request.getRequestURI() + "]");
    }

    private TextValueModel toView(Domain domain) {
        ActionFlow f = (ActionFlow) domain;
        return TextValueModel.builder().value(f.getEndAction().getCode()).text(f.getEndAction().getName()).build();
    }

    public static Map getMap(String k, String l) {
        Map m = new HashMap();
        m.put ("key", k);
        m.put ("label", l);
        return m;
    }

}
