package ro.x13.asig.db.dao.catalog;


import ro.x13.asig.db.dao.builder.HqlSimpleQueryBuilder;
import ro.x13.asig.db.dao.domain.Tara;
import ro.x13.asig.db.view.model.TextValueModel;

import javax.persistence.Query;
import java.util.List;

import static ro.x13.asig.db.view.model.TextValueModel.cName;


public class TaraRepositoryImpl  extends BaseRepository {


    //@Query("select new" + cName + " (u.name, u.id) from Tara u where u.endDate is null order by u.name")


    public List<TextValueModel> findModel2() {
        Query query = em.createQuery("SELECT new "+cName+" (u.id , u.name) from Tara u where u.endDate is null order by u.name", TextValueModel.class);
        //query.setParameter(1, firstName + "%");
        return query.getResultList();
    }

    public List<TextValueModel> findModel() {
        return  new HqlSimpleQueryBuilder(TextValueModel.class)
                .select("i.id", "i.name")
                .from(Tara.class, "i")
                /*.leftJoin("i.startUser", "s")
                .leftJoin("i.destIndex", "d")
                .leftJoin("i.sourceIndex", "si")
                .andDateMax("i.startDate")
                .andDateMin("i.endDate")
                .and("i.assignmentType", assType)
                .and("i.portfolioType", portfolioType)
                 */
                .orderBy("i.name")
                .build(em)
                .getResultList();
    }



}

