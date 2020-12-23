package ro.x13.asig.db.view.meta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * abstract class for pagining components
 */
public abstract class PagingView {

    private int page;
    private int rowPerPage = 5;


    public void pagePrevious (){
        page--;
        load();
    }

    /**
     * reload data of this pageable component
     */
    protected abstract void load();

    public void pageNext (){
        page++;
        load();
    }
}
