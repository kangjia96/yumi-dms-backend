package cn.ikangjia.yumi.dms.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
public class ResultHandler {
    public static List<Map<String, Object>> doMapResult(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        List<Map<String, Object>> result = new ArrayList<>(columnCount);
        while (rs.next()) {
            Map<String, Object> temMap = new HashMap<>();
            for (int i = 0; i < columnCount; i++) {
                temMap.put(metaData.getColumnLabel(i + 1), rs.getObject(i + 1));
            }
            result.add(temMap);
        }
        return result;
    }

    public static  <T> List<T> doObjectResult(Class<T> t, ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        Field[] fields = t.getDeclaredFields();

        List<T> result = new ArrayList<>(columnCount);
        while (rs.next()) {
            T obj = t.getDeclaredConstructor().newInstance();
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(obj, String.valueOf(rs.getObject(field.getName())));
            }
            result.add(obj);
        }
        return result;
    }

}
