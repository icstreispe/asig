package ro.x13.asig.db.view;

import java.util.List;


//TODO paging goes here also?
public interface ListView {

    String getName();

    List getList();

    List getHeaders();

    String del(Long id);
}
