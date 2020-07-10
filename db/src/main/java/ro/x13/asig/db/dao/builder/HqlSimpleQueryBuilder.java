package ro.x13.asig.db.dao.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static ro.x13.asig.db.dao.builder.HqlSimpleQueryBuilder.Type.*;
import static ro.x13.asig.db.dao.builder.Pair.newPair;


/**
 * class for generating simple queries
 * with nullable params
 */
public class HqlSimpleQueryBuilder<T> {


    private static final Logger logger = LoggerFactory.getLogger(HqlSimpleQueryBuilder.class);
    private static final String PARAM = "param";

    private Class<T> clazz;
    private List<String> select = new ArrayList<>();
    private List<Pair<String, String>> orderByList = new ArrayList<>();
    private List<Pair<Class, String>> root = new ArrayList<>();
    private List<Pair<String, String>> leftJoin = new ArrayList<>();
    private List<Tuple> filters = new ArrayList<>();

    public HqlSimpleQueryBuilder(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * value can be null in order to select ALL rows
     *
     * " and (value is null OR fieldRef = value) "
     */
    public HqlSimpleQueryBuilder and(String fieldRef, Object value) {
        filters.add(new Tuple(fieldRef, value, NORMAL));
        return this;
    }

    /**
     * " and (fieldRef in (value) OR value is null) "
     */
    public HqlSimpleQueryBuilder and(String fieldRef, Collection value) {
        filters.add(new Tuple(fieldRef, value, LIST));
        return this;
    }

    //TODO add different orders
    public HqlSimpleQueryBuilder<T> orderBy(String fieldRef) {
        orderByList.add(newPair(fieldRef, "ASC"));
        return this;
    }

    public HqlSimpleQueryBuilder<T> leftJoin(String fieldRef, String alias) {
        leftJoin.add(newPair(fieldRef, alias));
        return this;
    }

    public HqlSimpleQueryBuilder<T> from(Class fieldRef, String alias) {
        root.add(newPair(fieldRef, alias));
        return this;
    }

    public HqlSimpleQueryBuilder<T> select(String... fieldRefs) {
        select.addAll(Arrays.asList(fieldRefs));
        return this;
    }

    public TypedQuery<T> build(EntityManager em) {
        StringBuilder sql = createSelect();
        sql.append(createFrom());
        sql.append (createJoins());
        sql.append (createWhere());
        sql.append (createOrderBy());

        logger.debug ("SQL: {}", sql);
        TypedQuery<T> q = em.createQuery(sql.toString(), clazz);
        injectParams(q);

        return q;
    }


    private StringBuilder createJoins() {
        StringBuilder sql = new StringBuilder();
        for (Pair<String, String> join : leftJoin) {
            sql.append(" LEFT OUTER JOIN ").append(join.getSource()).append(" ").append(join.getTarget()).append("\n");
        }
        return sql;
    }

    /**
     * injecting params
     */
    private void injectParams(TypedQuery<T> q) {
        for (int i = 0; i < filters.size(); i++) {
            Tuple filter = filters.get(i);
            if (filter.getType() == Type.NORMAL) {
                q.setParameter(PARAM + i, filter.getValue());
            } else if (filter.getType() == LIST){
                //q.unwrap(org.hibernate.Query.class).setParameterList(PARAM + i, (Collection) filter.getValue());
                q.setParameter(PARAM + i, (Collection) filter.getValue());
            }
        }
    }

    private StringBuilder createOrderBy() {
        StringBuilder sql = new StringBuilder();
        for (int i = 0; i < orderByList.size(); i++) {
            Pair<String, String> orderBy = orderByList.get(i);
            if (i == 0) {
                sql.append(" ORDER BY ");
            } else {
                sql.append(", ");
            }
            sql.append(orderBy.getSource()).append(" ").append(orderBy.getTarget());
        }
        return sql;
    }

    /**
     * TODO add variabile date, not just current
     */
    private StringBuilder createWhere() {
        StringBuilder sql = new StringBuilder();
        sql.append(" WHERE 1=1").append("\n");
        for (int i = 0; i < filters.size(); i++) {
            Tuple filter = filters.get(i);
            if (filter.getType() == NORMAL) {
                sql.append(" AND (").append(filter.getFieldRef()).append(" = ").append(":").append(PARAM).append(i).append(" OR :").append(PARAM).append(i).append(" is NULL)\n");
            } else if (filter.getType() == LIST) {
                sql.append(" AND ").append(filter.getFieldRef()).append(" IN ").append(":").append(PARAM).append(i).append("\n");
            } else if (filter.getType() == DATE_MAX && filter.getValue() == null) { // and (i.startDate is null OR i.startDate <= CURRENT_DATE)
                sql.append(" AND (").append(filter.getFieldRef()).append(" IS NULL OR ").append(filter.getFieldRef()).append(" <= CURRENT_DATE )").append("\n");
            } else if (filter.getType() == DATE_MAX && filter.getValue() != null) { // and (i.startDate is null OR i.startDate <= :paramX)
                sql.append(" AND (").append(filter.getFieldRef()).append(" IS NULL OR ").append(filter.getFieldRef()).append(" <= :").append(PARAM).append(i).append(" )").append("\n");
            } else if (filter.getType() == DATE_MIN && filter.getValue() == null) { // and (i.endDate is null OR i.startDate > CURRENT_DATE)
                sql.append(" AND (").append(filter.getFieldRef()).append(" IS NULL OR ").append(filter.getFieldRef()).append(" > CURRENT_DATE )").append("\n");
            } else if (filter.getType() == DATE_MIN && filter.getValue() != null) { // and (i.endDate is null OR i.startDate > :paramX)
                sql.append(" AND (").append(filter.getFieldRef()).append(" IS NULL OR ").append(filter.getFieldRef()).append(" > :").append(PARAM).append(i).append(")").append("\n");
            }
        }
        return sql;
    }

    /**
     *  " FROM IndexMapping i "
     */
    private StringBuilder createFrom() {
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ").append(root.get(0).getSource().getSimpleName()).append(" ").append(root.get(0).getTarget()).append("\n");
        return sql;
    }

    private StringBuilder createSelect() {
        StringBuilder sql = new StringBuilder("SELECT ");
        if (!isScalar()) {
            sql.append(" new ")
                    .append(clazz.getName())
                    .append(" (");
        }
        for (int i = 0; i < select.size(); i++) {
            String fieldRef = select.get(i);
            if (i != 0){
                sql.append(", ");
            }
            sql.append(fieldRef);
        }
        if (!isScalar()) {
            sql.append(") ");
        }
        sql.append("\n");
        return sql;
    }

    private boolean isScalar() {
        return clazz == String.class;
    }

    /**
     * "and (i.startDate is null OR i.startDate <= CURRENT_DATE) "
     */
    public HqlSimpleQueryBuilder<T> andDateMax(String fieldRef) {
        filters.add(new Tuple(fieldRef, null, DATE_MAX));
        return this;
    }

    /**
     * "and (i.startDate is null OR i.startDate <= date) "
     */
    public HqlSimpleQueryBuilder<T> andDateMax(String fieldRef, Date date) {
        filters.add(new Tuple(fieldRef, date, DATE_MAX));
        return this;
    }

    /**
     * "and (i.endDate is null OR i.startDate > CURRENT_DATE) "
     */
    public HqlSimpleQueryBuilder<T> andDateMin(String fieldRef) {
        filters.add(new Tuple(fieldRef, null, DATE_MIN));
        return this;
    }

    /**
     * "and (i.endDate is null OR i.startDate > date) "
     */
    public HqlSimpleQueryBuilder<T> andDateMin(String fieldRef, Date date) {
        filters.add(new Tuple(fieldRef, date, DATE_MIN));
        return this;
    }

    enum Type {
        NORMAL,
        DATE_MIN,
        DATE_MAX,
        LIST
    }

    final class Tuple {

        private final String fieldRef;
        private final Object value;
        private final Type type;

        public Tuple(String fieldRef, Object value, Type type) {
            this.fieldRef = fieldRef;
            this.value = value;
            this.type = type;
        }

        public String getFieldRef() {
            return fieldRef;
        }

        public Object getValue() {
            return value;
        }

        public Type getType() {
            return type;
        }
    }


}
