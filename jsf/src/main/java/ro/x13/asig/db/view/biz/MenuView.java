package ro.x13.asig.db.view.biz;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ro.x13.asig.db.filter.RequestScopedController;
import ro.x13.asig.db.service.meta.MenuService;
import ro.x13.asig.db.view.jsf.HeaderView;

import java.util.List;

@RequestScopedController("menu")
@RequiredArgsConstructor
@Getter
@Setter
public class MenuView  {


    private final MenuService menuService;


    public List getList() {
        return HeaderView.builder()
                .add("/index.xhtml", "Acasa")
                .add( "/polita.xhtml", "Polita")
                .add("/auto/list.xhtml", "Auto")
                .build();
    }

}
