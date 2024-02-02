package cn.ikangjia.yumi.dms.core.constants;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
public interface TableConstants {
    enum TableType{
        BASE_TABLE("TABLE_NAME"),
        VIEW("VIEW"),
        SYSTEM_VIEW("SYSTEM_VIEW"),
        ;

        private final String tableType;

        TableType(String tableType) {
            this.tableType = tableType;
        }

        public String getTableType() {
            return tableType;
        }
    }
}
