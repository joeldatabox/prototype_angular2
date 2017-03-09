package br.com.maxxsoft.repository.infra;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Created by master on 06/03/17.
 */
public class QueryBuilder {


    public static Query createQuery(Map<String, String> queryParams) {
        Map<String, Map<Operators, Object>> trimap = new HashMap<>();

        queryParams.forEach((key, value) -> {
            String k = key(key);

            if (key.endsWith(Operators.GTE.toString())) {
                addParam(trimap, k, Operators.GTE, value);
            }
            if (key.endsWith(Operators.LT.toString())) {
                addParam(trimap, k, Operators.LT, value);
            }
            if (key.endsWith(Operators.IN.toString())) {
                addParam(trimap, k, Operators.IN, split(value));
            }
            if (key.endsWith(Operators.IS.toString()) || !key.contains("$") ) {
                addParam(trimap, k, Operators.IS, value);
            }
            if (key.endsWith(Operators.PAGE.toString())) {
                addParam(trimap, k, Operators.PAGE, value);
            }
            if (key.endsWith(Operators.LIMIT.toString())) {
                addParam(trimap, k, Operators.LIMIT, value);
            }
            if (key.endsWith(Operators.ORDER_BY_ASC.toString())) {
                addParam(trimap, k, Operators.ORDER_BY_ASC, split(value));
            }
            if (key.endsWith(Operators.ORDER_BY_DESC.toString())) {
                addParam(trimap, k, Operators.ORDER_BY_DESC, split(value));
            }
        });
        Query query = new Query();
        trimap.forEach((key, map) -> {
            Criteria cri = where(key);
            map.forEach((operation, value) -> {
                switch (operation) {
                    case GTE:
                        cri.gte(value);
                        break;
                    case LT:
                        cri.lt(value);
                        break;
                    case IN:
                        cri.in((Object[]) value);
                        break;
                    case IS:
                        cri.is(value);
                        break;
                    case PAGE:
                        query.skip(Integer.valueOf(value.toString()));
                        break;
                    case LIMIT:
                        //query.with()
                        query.limit(Integer.valueOf(value.toString()));
                        break;
                    case ORDER_BY_ASC:
                        query.with(new Sort(Sort.Direction.ASC, split(value.toString())));
                        break;
                    case ORDER_BY_DESC:
                        query.with(new Sort(Sort.Direction.DESC, split(value.toString())));
                        break;
                    default:
                        throw new IllegalArgumentException("Case not foud");
                }
            });
            query.addCriteria(cri);
        });
        return query;
    }

    private static void addParam(Map<String, Map<Operators, Object>> trimap, String key, Operators operator, Object value) {
        if (trimap.containsKey(key)) {
            trimap.get(key).put(operator, value);
        } else {
            HashMap<Operators, Object> mp = new HashMap<>();
            mp.put(operator, value);
            trimap.put(key, mp);
        }
    }

    private static String key(String key) {
        for (Operators o : Operators.values()) {
            key = key.replace(o.toString(), "");
        }
        return key;
    }

    private static String[] split(String value) {
        if (value != null && !value.isEmpty()) {
            if (value.contains(";"))
                return value.split(";");
            if (value.contains("|"))
                return value.split("|");

            return new String[]{value};
        }
        return null;
    }


}
