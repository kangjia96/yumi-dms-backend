package cn.ikangjia.yumi.dms.core.sqlbuilder.table;

import cn.ikangjia.yumi.dms.api.dto.table.ColumnCreateDTO;
import cn.ikangjia.yumi.dms.core.entity.TableEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
public class TableSQLBuilder {


    public static String getCreateSQL(TableEntity tableEntity, List<ColumnCreateDTO> columnDTOList) {
        String databaseName = tableEntity.getDatabaseName();
        String tableName = tableEntity.getTableName();
        String comment = tableEntity.getComment();
        String engine = tableEntity.getEngine();

        StringBuilder sb = new StringBuilder();

        // create table db_xx.t_person (
        sb.append("create table ").append(databaseName).append(".").append(tableName).append(" ( ");

        // 列字段信息
        String columnStr = getColumnStr(columnDTOList);

        sb.append(columnStr)
                .append(" ) ");

        // 注释
        if (StringUtils.hasText(comment)) {
            sb.append(" comment='").append(comment).append("'");
        }

        // 引擎
        if (StringUtils.hasText(engine)) {
            sb.append(" engine='").append(comment).append("'");
        }

        sb.append(";");
        return sb.toString();
    }

    private static String getColumnStr(List<ColumnCreateDTO> columnDTOList) {
        if (ObjectUtils.isEmpty(columnDTOList) || columnDTOList.isEmpty()) {
            throw new RuntimeException("列参数有误");
        }

        StringBuilder columnSB = new StringBuilder();
        columnDTOList.forEach(columnInfo -> {
            System.out.println(columnInfo);
            columnSB.append(columnInfo.getColumnName()).append(" ")
                    .append(columnInfo.getTypeInfo());

            if (columnInfo.isNull()) {
                columnSB.append(" NULL ");
            } else {
                columnSB.append(" NOT NULL ");
            }

            if (columnInfo.isUnique()) {
                columnSB.append(" unique ");
            }

            if (StringUtils.hasText(columnInfo.getDefaultValue())) {
                columnSB.append(" DEFAULT ").append("'")
                        .append(columnInfo.getDefaultValue()).append("'");
            }

            if (columnInfo.isIncrement()){
                columnSB.append(" AUTO_INCREMENT ");
            }
            columnSB.append(",");
        });
        String columnStr = columnSB.toString();

        List<String> primaryColumn = columnDTOList.stream()
                .filter(ColumnCreateDTO::isPrimaryKey)
                .map(ColumnCreateDTO::getColumnName)
                .toList();
        if (primaryColumn.isEmpty()) {
            columnStr = columnStr.substring(0, columnStr.length() - 1);
        }else {
            String collect = String.join(",", primaryColumn);
            String primaryStr = "PRIMARY KEY ( " + collect + ")";
            columnStr = columnStr + primaryStr;
        }
        return columnStr;
    }
}
