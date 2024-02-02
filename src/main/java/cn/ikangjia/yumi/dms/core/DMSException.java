package cn.ikangjia.yumi.dms.core;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
public class DMSException extends RuntimeException{

    public DMSException(String message) {
        super(message);
    }

    public DMSException(String message, Throwable cause) {
        super(message, cause);
    }
}
