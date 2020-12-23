package ro.x13.asig.db.view;

import java.util.List;


//TODO paging goes here also?
public interface ListView {

    /**
     * prefix for the keys of the properties specific of this component
     */
    String getName();

    List getList();

    List getHeaders();

    String del(Long id);
}
